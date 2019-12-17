package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		
		PreparedStatement ps = null;
		try {
			
			ps = conn.prepareStatement(
					  "INSERT INTO seller"
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES"
					+ "(?, ?, ?, ?, ?);"
					, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getEmail());
			ps.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			ps.setDouble(4, obj.getBaseSalary());
			ps.setInt(5, obj.getDepartment().getId());
			
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected  > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}			
				DB.closeResultSet(rs);
				
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
		}		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					  "SELECT seller.*,department.Name as DepName " 
					+ "FROM seller INNER JOIN department " 
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?"
					);
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				
				Department dep = instantieteDepartment(rs);						
				Seller seller = instantieteSeller(rs, dep);
				
				return seller;
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		return null;
	}

	private Seller instantieteSeller(ResultSet rs, Department dep) throws SQLException{
		
		Seller seller = new Seller();
		
		seller.setId(rs.getInt("id"));
		seller.setName(rs.getString("name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setDepartment(dep);
		
		return seller;
	}

	private Department instantieteDepartment(ResultSet rs) throws SQLException{
		
		Department dep = new Department();
		
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		
		return dep;
	}

	@Override
	public List<Seller> findAll() {
				
		try {
			
			Statement ps = conn.createStatement();
			
			ResultSet rs = ps.executeQuery(
					  "SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name "
					);
			
			List<Seller> list = new ArrayList<>();
			/* o map irá garantir que apenas um objeto departament seja instânciado para garantir
			 * a relação um para muitos. No caso o while enquanto for válido irá atribuir
			 * sempre o mesmo objeto department presente na chave de valor que será igual ao id de department.
			 * Oo primeiro laço map irá retornar um valor nulo porque nenhum um valor foi inserido. Em seguida
			 * o obj Department torna condição de nulo valida. Então um objeto Department é instânciado através
			 * do retorno do método instantieteDepartment(rs) que recebe o resultset atual e a partir dele 
			 * retorna um um objeto department e em seguida o guarda na estrutura map, como chave
			 * recebe o id do departmento e como valor o objeto department. Em seguida um objeto seller é retornado
			 * a partir do método instantieteSeller(rs, dep) que recebe um resultSet e o objeto departamento (que será
			 * sempre um mesmo objeto Department). Por fim o objeto Seller é adicionado a lista ao fim do loop a lista
			 * é retornada. 
			 * */
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantieteDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller obj = instantieteSeller(rs, dep);
				list.add(obj);				
			}
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					  "SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name"
					);
			
			st.setInt(1, department.getId());
			
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while (rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentID"));
				
				if (dep == null) {
					dep = instantieteDepartment(rs);
					map.put(rs.getInt(rs.getInt("DepartmentID")), dep);
				}
								
				Seller obj = instantieteSeller(rs, dep);
				list.add(obj);
				
			}
			
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}		
	}	
}
