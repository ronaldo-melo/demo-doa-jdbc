package model.entities.SQLStrategy.Implementation;

import java.util.List;
import java.util.Scanner;

import model.entities.Seller;
import model.entities.SQLStrategy.SellerPersistence;
import services.SellerService;

public class FindAllSellers implements SellerPersistence {

	@Override
	public void persistence(Scanner sc, SellerService sellerService) {		
		List<Seller> list = sellerService.findAll();
		list.stream().forEach(System.out::println);
		System.out.println("Done! All sellers listed!\n");
	}

}
