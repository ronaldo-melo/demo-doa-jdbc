package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	
	public static void main(String[] args) throws ParseException{
			
		SellerDao sellerDao = DaoFactory.createSellerDao(); 
		
		System.out.println("=== TEST 1: seller findById =====");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
	
		System.out.println("\n=== TEST 2: seller findByDepartment =====\n");
		Department department = new Department(1, null);
		
		System.out.println("\n=== TEST 3: seller insert =====\n");
		List<Seller> list = sellerDao.findByDepartment(department);
		list.stream().forEach(System.out::println);
		
		System.out.println("\n=== TEST 4: seller insert =====\n");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", sdf.parse("22/06/1994"), 4000.0, department);
		sellerDao.insert(newSeller);
		
		System.out.println("Inserted! New id = " + newSeller.getId());
		
	}
	
}
