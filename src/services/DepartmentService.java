package services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {
	
	private DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
	
	public void insert(Department department) {
		departmentDao.insert(department);
	}
	
	public void update(Department department) {
		departmentDao.update(department);
	}
	
	public void delete(Integer id) {
		departmentDao.delete(id);
	}
	
	public Department findById(Integer id) {
		return departmentDao.findById(id);
	}
	
	public List<Department> findAll(){
		return departmentDao.findAll();
	}
}
