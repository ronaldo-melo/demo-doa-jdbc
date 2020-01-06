package model.entities.SQLStrategy;

import java.util.Scanner;

import services.SellerService;

public interface SellerPersistence {
	
	public void persistence(Scanner sc, SellerService sellerService);
	
}
