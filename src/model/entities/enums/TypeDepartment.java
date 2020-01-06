package model.entities.enums;

import model.entities.SQLStrategy.DepartmentPersistence;

public enum TypeDepartment {
	
	CREATE_DEPARTMENT {
		@Override
		public DepartmentPersistence getDepartmentPersistence() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	
	FIND_DEPARTMENT {
		@Override
		public DepartmentPersistence getDepartmentPersistence() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	
	UPDATE_DEPARTMENT{
		@Override
		public DepartmentPersistence getDepartmentPersistence() {
			// TODO Auto-generated method stub
			return null;
		}
	},	
	
	FIND_ALL_DEPARTMENTS {
		@Override
		public DepartmentPersistence getDepartmentPersistence() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	public abstract DepartmentPersistence getDepartmentPersistence();
	
}
