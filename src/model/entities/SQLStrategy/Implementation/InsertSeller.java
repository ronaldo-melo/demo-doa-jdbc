package model.entities.SQLStrategy.Implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import db.DbException;
import model.entities.Department;
import model.entities.Seller;
import model.entities.SQLStrategy.SellerPersistence;
import services.SellerService;

public class InsertSeller implements SellerPersistence {

	@Override
	public void persistence(Scanner sc, SellerService sellerService) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date birthDate = null;
		System.out.print("Enter Seller name: ");
		String name = sc.nextLine();
		System.out.print("Enter Seller email: ");
		String email = sc.nextLine();
		System.out.print("Enter Seller BirthDate: ");

		try {
			birthDate = sdf.parse(sc.nextLine());
		} catch (ParseException e) {
			throw new DbException(e.getMessage());
		}

		System.out.print("Enter Seller base salary: ");
		double baseSalary = sc.nextDouble();
		System.out.print("Enter Seller DepartmentId: ");
		int id = sc.nextInt();

		Seller seller = new Seller(id, name, email, birthDate, baseSalary, new Department(id, null));
		sellerService.insert(seller);

		System.out.println("\nDone! Seller registred!\n");

	}

}
