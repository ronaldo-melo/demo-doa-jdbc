package model.entities.SQLStrategy.Implementation;

import java.util.Scanner;

import model.entities.Seller;
import model.entities.SQLStrategy.SellerPersistence;
import services.SellerService;

public class FindByIdSeller implements SellerPersistence{

	@Override
	public void persistence(Scanner sc, SellerService sellerService) {
		System.out.print("Enter seller id: ");
		int id = sc.nextInt();
		Seller seller = sellerService.findById(id);
		System.out.println(seller);
		System.out.println("Done! Seller finded!\n");
	}

}
