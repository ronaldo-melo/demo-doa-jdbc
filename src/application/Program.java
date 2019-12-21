package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
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
		DepartmentDao departmentDao = DaoFactory.createDepartmentDo();
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
					// Escolher entre as opções de Seller Crud
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
							System.out.print("Enter seller id: ");
							int id = sc.nextInt();
							seller = sellerDao.findById(id);
							System.out.println(sellerDao.findById(id));
							break;

						// Update Seller
						case 3:
							System.out.print("Enter seller id: ");
							id = sc.nextInt();
							sc.nextLine();
							seller = sellerDao.findById(id);
							updateSeller(sdf, sellerDao, sc, seller);
							break;

						// Delete Seller
						case 4:
							System.out.print("Enter seller id to delete: ");
							id = sc.nextInt();
							sellerDao.delete(id);
							System.out.println("Done! Seller deleted!\n");
							break;

						// List all Seller's
						case 5:
							System.out.println("\n-----------------ALL SELLERS-------------------");
							List<Seller> list = sellerDao.findAll();
							list.stream().forEach(System.out::println);
							break;

						// Exit
						default:
							System.out.println("\nLiving Seller C.R.U.D\n");
						}

					} while (sellerMenu >= 1 && sellerMenu <= 4);

				case 2:
					int departmentMenu;
					do {
						showDepartmentCrudOption();
						departmentMenu = sc.nextInt();
						sc.nextLine();
						switch (departmentMenu) {

						case 1:
							System.out.print("Enter department name: ");
							String name = sc.nextLine();
							departmentDao.insert(new Department(null, name));
							System.out.println("\nDone! Department registred!\n");
							break;

						case 2:
							System.out.print("Enter deparment id: ");
							int id = sc.nextInt();
							Department department = departmentDao.findById(id);
							System.out.println("\n" + department.toString());
							break;

						case 3:
							System.out.print("Enter deparment id to update: ");
							id = sc.nextInt();
							sc.nextLine();
							department = departmentDao.findById(id);
							System.out.print("Enter new department name: ");
							department.setName(sc.nextLine());
							departmentDao.update(department);
							System.out.println("\nDone! Department updated.\n");
							break;
						}

					} while (departmentMenu == 1 || departmentMenu == 2);

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

	private static void updateSeller(SimpleDateFormat sdf, SellerDao sellerDao, Scanner sc, Seller seller)
			throws ParseException {

		System.out.print("Enter new Seller name: ");
		seller.setName(sc.nextLine());

		System.out.print("Enter new Seller email: ");
		seller.setEmail(sc.nextLine());

		System.out.print("Enter new Seller BirthDate: ");
		seller.setBirthDate(sdf.parse(sc.nextLine()));

		System.out.print("Enter new Seller base salary: ");
		seller.setBaseSalary(sc.nextDouble());

		System.out.print("Enter new Seller DepartmentId: ");
		seller.getDepartment().setId(sc.nextInt());

		sellerDao.update(seller);

		System.out.println("\nDone! Seller updated!\n");
	}
}
