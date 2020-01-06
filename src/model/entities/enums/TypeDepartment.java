package model.entities.enums;

import model.entities.SQLStrategy.DepartmentPersistence;
import model.entities.SQLStrategy.Implementation.department.DepartmentFindById;
import model.entities.SQLStrategy.Implementation.department.DepartmentUpdate;
import model.entities.SQLStrategy.Implementation.department.FindAllDepartments;
import model.entities.SQLStrategy.Implementation.department.InsertDepartment;

public enum TypeDepartment {
	
	CREATE_DEPARTMENT {
		@Override
		public DepartmentPersistence getDepartmentPersistence() {
			return new InsertDepartment();
		}
	},
	
	FIND_DEPARTMENT {
		@Override
		public DepartmentPersistence getDepartmentPersistence() {
			return new DepartmentFindById();
		}
	},
	
	UPDATE_DEPARTMENT{
		@Override
		public DepartmentPersistence getDepartmentPersistence() {			
			return new DepartmentUpdate();
		}
	},	
	
	FIND_ALL_DEPARTMENTS {
		@Override
		public DepartmentPersistence getDepartmentPersistence() {
			return new FindAllDepartments();
		}
	};
	
	public abstract DepartmentPersistence getDepartmentPersistence();
	
}
