package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Department;
import model.entities.Seller;
import model.entities.SQLStrategy.SellerPersistence;
import model.entities.enums.TipoSeller;
import services.DepartmentService;
import services.SellerService;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DepartmentService departmentService = new DepartmentService();
		SellerService sellerService = new SellerService();
		Seller seller = null;
		Department department = null;
		int id;
		SellerPersistence sellerPersistence;
		
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
							TipoSeller tsp = TipoSeller.values()[sellerMenu - 1];
							SellerPersistence sp = tsp.getSellerPesistence();							
							sp.persistence(sc, sellerService);							
															
							break;
							
							// Find Seller	
						case 2:								
							System.out.print("Enter seller id: ");
							id = sc.nextInt();
							seller = sellerService.findById(id);
							System.out.println(seller.toString());
							System.out.println("Done! Seller finded!");
							break;

						//Update Seller
						case 3:							
							System.out.print("Enter seller id: ");
							id = sc.nextInt();
							//find
							sc.nextLine();
							seller = sellerService.findById(id);							
							//update
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
							sellerService.update(seller);
							System.out.println("\nDone! Seller updated!\n");							
							break;

						// Delete Seller
						case 4:							
							System.out.print("Enter seller id to delete: ");
							id = sc.nextInt();
							sellerService.delete(id);
							System.out.println("Done! Seller deleted!\n");
							break;

						// List all Seller's
						case 5:
							System.out.println("\n-----------------ALL SELLERS-------------------");
							List<Seller> list = sellerService.findAll();
							list.stream().forEach(System.out::println);
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
							System.out.print("Enter department name: ");
							String name = sc.nextLine();
							department = new Department(null, name);
							departmentService.insert(department);							
							System.out.println("\nDone! Department registred!\n");
							break;

						case 2:						
							System.out.print("Enter deparment id: ");
							id = sc.nextInt();
							department = departmentService.findById(id);
							System.out.println("\n" + department.toString());							
							System.out.println("\nDone! Department finded!\n");
							break;

						case 3:							
							System.out.print("Enter deparment id to update: ");
							id = sc.nextInt();
							sc.nextLine();
							department = departmentService.findById(id);
							System.out.print("Enter new department name: ");
							department.setName(sc.nextLine());
							departmentService.update(department);
							System.out.println("\nDone! Department updated.\n");
							break;

						case 4:
							System.out.println("\n-----------------ALL DEPARTMENTS-------------------");							
							List<Department> list = departmentService.findAll();
							list.forEach(System.out::println);
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
}
