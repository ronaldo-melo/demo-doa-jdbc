package model.dao;

import java.util.List;

public interface Dao <T>{
	
	void insert(T t);
	
	void update(T t);
	
	void delete(Integer id);
	
	T findById(Integer id);
	
	List<T> findAll();
	
}
