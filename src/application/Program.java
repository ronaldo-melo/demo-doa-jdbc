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
		DepartmentService departmentService = new DepartmentService();
		SellerService sellerService = new SellerService();

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

						TipoSeller tsp = TipoSeller.values()[sellerMenu - 1];
						SellerPersistence sp = tsp.getSellerPesistence();
						sp.persistence(sc, sellerService);

						if (sellerMenu == 5) {
							System.out.println("\n-----------------ALL SELLERS-------------------");
							List<Seller> list = sellerService.findAll();
							list.stream().forEach(System.out::println);
						}

					} while (sellerMenu >= 1 && sellerMenu <= 5);
					System.out.println("\nLiving Seller C.R.U.D\n");
					break;

				case 2:
					int departmentMenu;
					do {
						ShowMessage.showDepartmentCrudOption();
						departmentMenu = sc.nextInt();
						sc.nextLine();
						
						
						
						if (departmentMenu == 4) {
							System.out.println("\n-----------------ALL DEPARTMENTS-------------------");
							List<Department> list = departmentService.findAll();
							list.forEach(System.out::println);
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
