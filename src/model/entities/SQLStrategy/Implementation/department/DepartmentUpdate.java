package model.entities.SQLStrategy.Implementation.department;

import java.util.Scanner;

import model.entities.Department;
import model.entities.SQLStrategy.DepartmentPersistence;
import services.DepartmentService;

public class DepartmentUpdate implements DepartmentPersistence {

	@Override
	public void persistence(Scanner sc, DepartmentService departmentService) {
		System.out.print("Enter deparment id to update: ");
		int id = sc.nextInt();
		sc.nextLine();
		Department department = departmentService.findById(id);
		System.out.print("Enter new department name: ");
		department.setName(sc.nextLine());
		departmentService.update(department);
		System.out.println("\nDone! Department updated.\n");
	}

}
