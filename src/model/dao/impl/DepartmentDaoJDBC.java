package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st = conn.prepareStatement(
					" INSERT INTO DEPARTMENT "
					+ "(NAME) "
					+ "VALUES "
					+ "(?);"
					, Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			}		
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}		
	}

	@Override
	public void update(Department obj) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(""
					+ "UPDATE Department "
					+ "SET name = ? "
					+ "WHERE id = ?");
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

	@Override
	public void delete(Integer id) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					  "DELETE FROM Department "
					+ "WHERE id = ?;"
					);
			
			st.setInt(1, id);
			
			int rows = st.executeUpdate();
			
			if(rows == 0) {
				throw new DbException("there is no deparments with this id in the sellers records!");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

	@Override
	public Department findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st = conn.prepareStatement(
					  "SELECT * FROM Department AS dep "
					+ "WHERE "
					+ "dep.id = ?;"
					);
			
			st.setInt(1, id);
			
			st.executeQuery();
			
			rs = st.getResultSet();
			
			if(rs.next()) {
				Department department = new Department();
				department.setId(rs.getInt("Id"));
				department.setName(rs.getString("Name"));
				DB.closeResultSet(rs);
				DB.closeStatement(st);
				return department;
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		return null;
	}

	@Override
	public List<Department> findAll() {
		
		Statement st = null;
		ResultSet rs = null;
		List<Department> list;
		
		
		try {
			
			st = conn.createStatement();
			
			st.executeQuery(
					  "SELECT * FROM Department;"
					);
			
			rs = st.getResultSet();
			list = new ArrayList<>();
			
			while(rs.next()) {
				
				Department department = new Department();
				
				department.setId(rs.getInt("id"));
				department.setName(rs.getString("Name"));
				
				list.add(department);
			}
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}
	


}
