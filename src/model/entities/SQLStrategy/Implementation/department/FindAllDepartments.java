package model.entities.SQLStrategy.Implementation.department;

import java.util.List;
import java.util.Scanner;

import model.entities.Department;
import model.entities.SQLStrategy.DepartmentPersistence;
import services.DepartmentService;

public class FindAllDepartments implements DepartmentPersistence {

	@Override
	public void persistence(Scanner sc, DepartmentService departmentService) {
		List<Department> list = departmentService.findAll();
		list.stream().forEach(System.out::println);
		System.out.println("Done! All Department listed!\n");
	}

}
