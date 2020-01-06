package model.entities.SQLStrategy.Implementation.department;

import java.util.Scanner;

import model.entities.Department;
import model.entities.SQLStrategy.DepartmentPersistence;
import services.DepartmentService;

public class DepartmentFindById implements DepartmentPersistence {

	@Override
	public void persistence(Scanner sc, DepartmentService departmentService) {
		System.out.print("Enter Department id: ");
		int id = sc.nextInt();
		Department department = departmentService.findById(id);
		System.out.println(department);			
		System.out.println("Done! Department finded!");
	}

}
