package model.entities.enums;

import model.entities.SQLStrategy.SellerPersistence;
import model.entities.SQLStrategy.Implementation.UpdateSeller;
import model.entities.SQLStrategy.Implementation.FindSellerById;
import model.entities.SQLStrategy.Implementation.InsertSeller;

public enum TipoSeller {
	
	CREATE_SELLER { 
		@Override
		public SellerPersistence getSellerPesistence() {
			return new InsertSeller();
		}
	},
	
	FIND_SELLER {
		@Override
		public SellerPersistence getSellerPesistence() {
			return new FindSellerById();
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
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	public abstract SellerPersistence getSellerPesistence();
	
}
