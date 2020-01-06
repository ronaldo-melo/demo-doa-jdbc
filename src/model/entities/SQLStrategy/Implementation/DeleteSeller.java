package model.entities.SQLStrategy.Implementation;

import java.util.Scanner;

import model.entities.SQLStrategy.SellerPersistence;
import services.SellerService;

public class DeleteSeller implements SellerPersistence{

	@Override
	public void persistence(Scanner sc, SellerService sellerService) {
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		sellerService.delete(id);
		System.out.println("Done! Delete completed!\n");
		
	}

}
