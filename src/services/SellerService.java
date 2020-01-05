package services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class SellerService{
	
	private SellerDao sellerDAO = DaoFactory.createSellerDao();
	
	public void insert(Seller seller) {
		sellerDAO.insert(seller);
	}
	
	public void update(Seller seller) {
		sellerDAO.update(seller);
	}
	
	public void delete(Integer id) {
		sellerDAO.delete(id);
	}
	
	public Seller findById(Integer id) {
		return sellerDAO.findById(id);
	}
	
	public List<Seller> findAll(){
		return sellerDAO.findAll();
	}

	
}
