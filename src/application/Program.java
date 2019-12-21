package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void showGetInfoFromMenu() {
		System.out.println("-----GET INFO FROM-----");
		System.out.println("1. Sellers");
		System.out.println("2. Department");
		System.out.println("3. Exit");
	}

	public static void showDepartmentCrudOption() {
		System.out.println("-----Choise one-----");
		System.out.println("1. Create Department");
		System.out.println("2. Find Department");
		System.out.println("3. Update Department");
		System.out.println("4. List all Department's");
		System.out.println("5. Exit");
	}

	public static void showSellerCrudOption() {
		System.out.println("-----Choise one-----");
		System.out.println("1. Create Seller");
		System.out.println("2. Find Seller");
		System.out.println("3. Update Seller");
		System.out.println("4. Delete Seller");
		System.out.println("5. List all Seller's");
		System.out.println("6. Exit");
	}

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller seller;
		
		
		try (Scanner sc = new Scanner(System.in)) {

			int choiceMenu;
			do {

				// Escolher entre SellersCrud e DepartmentCrud
				showGetInfoFromMenu();
				choiceMenu = sc.nextInt();

				switch (choiceMenu) {

				case 1:
					int sellerMenu;
					// Escolher entre as op��es de Seller Crud
					do {
						showSellerCrudOption();
						sellerMenu = sc.nextInt();
						sc.nextLine();

						switch (sellerMenu) {

						// Create Seller
						case 1:
							registerSeller(sdf, sellerDao, sc);
							break;

						case 2:
							// Find Seller
							System.out.print("Enter id seller: ");
							int id = sc.nextInt();							
							seller = sellerDao.findById(id);							
							System.out.println(seller);
							
							break;

						// Update Seller
						case 3:

							break;

						// Delete Seller
						case 4:

							break;

						// List all Seller's
						case 5:

							break;

						// Exit
						default:
							System.out.println("\nLiving Seller C.R.U.D\n");
						}

					} while (sellerMenu >= 1 && sellerMenu <= 4);

				case 2:
					// CRUD logic of the departments

				}

			} while (choiceMenu == 1 || choiceMenu == 2);
			System.out.println("\nLiving C.R.U.D system...\nThank you for use our services! ;-)");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void registerSeller(SimpleDateFormat sdf, SellerDao sellerDao, Scanner sc) throws ParseException {

		System.out.print("Enter Seller name: ");
		String name = sc.nextLine();
		System.out.print("Enter Seller email: ");
		String email = sc.nextLine();
		System.out.print("Enter Seller BirthDate: ");
		Date birthDate = sdf.parse(sc.nextLine());
		System.out.print("Enter Seller base salary: ");
		double baseSalary = sc.nextDouble();
		System.out.print("Enter Seller DepartmentId : ");
		int id = sc.nextInt();

		Seller seller = new Seller(id, name, email, birthDate, baseSalary, new Department(id, null));
		sellerDao.insert(seller);

		System.out.println("\nDone! Seller registered!\n");
	}

}
