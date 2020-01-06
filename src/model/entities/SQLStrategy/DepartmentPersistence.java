package model.entities.SQLStrategy;

import java.util.Scanner;

import services.DepartmentService;

public interface DepartmentPersistence {

	public void persistence(Scanner sc, DepartmentService departmentService);
	
}
