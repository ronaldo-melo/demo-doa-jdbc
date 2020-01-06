package model.entities.enums;

import model.entities.SQLStrategy.SellerPersistence;
import model.entities.SQLStrategy.Implementation.seller.DeleteSeller;
import model.entities.SQLStrategy.Implementation.seller.FindAllSellers;
import model.entities.SQLStrategy.Implementation.seller.FindByIdSeller;
import model.entities.SQLStrategy.Implementation.seller.SellerInsert;
import model.entities.SQLStrategy.Implementation.seller.UpdateSeller;

public enum TypeSeller {
	
	CREATE_SELLER { 
		@Override
		public SellerPersistence getSellerPesistence() {
			return new SellerInsert();
		}
	},
	
	FIND_SELLER {
		@Override
		public SellerPersistence getSellerPesistence() {
			return new FindByIdSeller();
		}
	},
	
	UPDATE_SELLER{
		@Override
		public SellerPersistence getSellerPesistence() {
			return new UpdateSeller();
		}
	},
	
	DELETE_SELLER {
		@Override
		public SellerPersistence getSellerPesistence() {
			return new DeleteSeller();
		}
	},
	
	FIND_ALL_SELLERS{
		@Override
		public SellerPersistence getSellerPesistence() {
			return new FindAllSellers();
		}
	};
	
	public abstract SellerPersistence getSellerPesistence();
	
}
