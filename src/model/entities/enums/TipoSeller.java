package model.entities.enums;

import model.entities.SQLStrategy.SellerPersistence;
import model.entities.SQLStrategy.Implementation.UpdateSeller;
import model.entities.SQLStrategy.Implementation.DeleteSeller;
import model.entities.SQLStrategy.Implementation.FindByIdSeller;
import model.entities.SQLStrategy.Implementation.SellerInsert;

public enum TipoSeller {
	
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
	};
	
	public abstract SellerPersistence getSellerPesistence();
	
}
