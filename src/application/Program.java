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



	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SellerDao sellerDao = DaoFactory.createSellerDao();
		DepartmentDao departmentDao = DaoFactory.createDepartmentDo();

		try (Scanner sc = new Scanner(System.in)) {

			int choiceMenu;
			do {

				// Escolher entre SellersCrud e DepartmentCrud
				ShowMessage.showGetInfoFromMenu();
				choiceMenu = sc.nextInt();

				switch (choiceMenu) {

				case 1:
					int sellerMenu;
					// Escolher entre as opções de Seller Crud
					do {
						ShowMessage.showSellerCrudOption();
						sellerMenu = sc.nextInt();
						sc.nextLine();

						switch (sellerMenu) {

						// Create Seller
						case 1:
							registerSeller(sdf, sellerDao, sc);
							break;

						case 2:
							// Find Seller
							findSeller(sellerDao, sc);
							break;

						// Update Seller
						case 3:
							updateSeller(sdf, sellerDao, sc);
							break;

						// Delete Seller
						case 4:
							deleteSeller(sellerDao, sc);
							break;

						// List all Seller's
						case 5:
							listAllSellers(sellerDao);
							break;

						// Exit
						default:
							System.out.println("\nLiving Seller C.R.U.D\n");
						}

					} while (sellerMenu >= 1 && sellerMenu <= 5);
					break;

				case 2:
					int departmentMenu;
					do {
						ShowMessage.showDepartmentCrudOption();
						departmentMenu = sc.nextInt();
						sc.nextLine();
						switch (departmentMenu) {

						case 1:
							createDepartment(departmentDao, sc);
							break;

						case 2:
							findDepartmentById(departmentDao, sc);
							break;

						case 3:
							updateDepartment(departmentDao, sc);
							break;

						case 4:
							System.out.println("\n-----------------ALL DEPARTMENTS-------------------");
							listAllDepartments(departmentDao);
							break;

						default:
							System.out.println("\nLiving Department C.R.U.D\n");

						}

					} while (departmentMenu >= 1 && departmentMenu <= 4);
					break;
				}

			} while (choiceMenu == 1 || choiceMenu == 2);
			System.out.println("\nLiving C.R.U.D system...\nThank you for use our services! ;-)");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void listAllDepartments(DepartmentDao departmentDao) {
		List<Department> list = departmentDao.findAll();
		list.forEach(System.out::println);
	}

	private static void updateDepartment(DepartmentDao departmentDao, Scanner sc) {
		System.out.print("Enter deparment id to update: ");
		int id = sc.nextInt();
		sc.nextLine();
		Department department = departmentDao.findById(id);
		System.out.print("Enter new department name: ");
		department.setName(sc.nextLine());
		departmentDao.update(department);
		System.out.println("\nDone! Department updated.\n");
	}

	private static void findDepartmentById(DepartmentDao departmentDao, Scanner sc) {
		System.out.print("Enter deparment id: ");
		int id = sc.nextInt();
		Department department = departmentDao.findById(id);
		System.out.println("\n" + department.toString());
	}

	private static void createDepartment(DepartmentDao departmentDao, Scanner sc) {
		System.out.print("Enter department name: ");
		String name = sc.nextLine();
		departmentDao.insert(new Department(null, name));
		System.out.println("\nDone! Department registred!\n");
	}

	private static void listAllSellers(SellerDao sellerDao) {
		System.out.println("\n-----------------ALL SELLERS-------------------");
		List<Seller> list = sellerDao.findAll();
		list.stream().forEach(System.out::println);
	}

	private static void deleteSeller(SellerDao sellerDao, Scanner sc) {
		System.out.print("Enter seller id to delete: ");
		int id = sc.nextInt();
		sellerDao.delete(id);
		System.out.println("Done! Seller deleted!\n");
	}

	private static void updateSeller(SimpleDateFormat sdf, SellerDao sellerDao, Scanner sc) throws ParseException {
		Seller seller;
		System.out.print("Enter seller id: ");
		int id = sc.nextInt();
		sc.nextLine();
		seller = sellerDao.findById(id);
		changeSeller(sdf, sellerDao, sc, seller);
	}

	private static void findSeller(SellerDao sellerDao, Scanner sc) {
		Seller seller;
		System.out.print("Enter seller id: ");
		int id = sc.nextInt();
		seller = sellerDao.findById(id);
		System.out.println(seller.toString());
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

	private static void changeSeller(SimpleDateFormat sdf, SellerDao sellerDao, Scanner sc, Seller seller)
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
