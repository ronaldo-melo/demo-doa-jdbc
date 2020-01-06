package model.entities.SQLStrategy.Implementation.department;

import java.util.Scanner;

import model.entities.Department;
import model.entities.SQLStrategy.DepartmentPersistence;
import services.DepartmentService;

public class InsertDepartment implements DepartmentPersistence{

	@Override
	public void persistence(Scanner sc, DepartmentService departmentService) {
		System.out.print("Enter department name: ");
		String name = sc.nextLine();
		departmentService.insert(new Department(null, name));
		System.out.println("\nDone! Department registred!\n");
	}

}
