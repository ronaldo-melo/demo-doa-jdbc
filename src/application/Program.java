package application;

import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.SQLStrategy.DepartmentPersistence;
import model.entities.SQLStrategy.SellerPersistence;
import model.entities.enums.TypeDepartment;
import model.entities.enums.TypeSeller;
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
						if(sellerMenu <= TypeSeller.values().length) {														
							TypeSeller tsp = TypeSeller.values()[sellerMenu - 1];
							SellerPersistence sp = tsp.getSellerPesistence();
							sp.persistence(sc, sellerService);
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
						
						if(departmentMenu <= TypeDepartment.values().length) {														
							TypeDepartment td = TypeDepartment.values()[departmentMenu - 1];
							DepartmentPersistence sp = td.getDepartmentPersistence();
							sp.persistence(sc, departmentService);
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
