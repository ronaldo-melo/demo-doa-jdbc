package model.entities.SQLStrategy.Implementation;

import java.util.Scanner;

import model.entities.Seller;
import model.entities.SQLStrategy.SellerPersistence;
import services.SellerService;

public class UpdateSeller implements SellerPersistence {

	@Override
	public void persistence(Scanner sc, SellerService sellerService) {		
		System.out.print("Enter seller id: ");
		Seller seller = sellerService.findById(sc.nextInt());
		sc.nextLine();		
		System.out.print("Enter seller new name: ");
		seller.setName(sc.nextLine());
		System.out.print("Enter seller new email: ");
		seller.setEmail(sc.nextLine());
		sellerService.update(seller);
		System.out.println("\nSeller Update completed!\n");		
	}

}
