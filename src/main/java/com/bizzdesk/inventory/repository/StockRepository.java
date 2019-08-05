package com.bizzdesk.inventory.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bizzdesk.inventory.model.Stock;



@Repository
public interface StockRepository extends CrudRepository<Stock, Long>{
	
	Stock findByStockId(String stockId);
	
	Stock findByBrandName(String brandName);
	
	Stock findByStockName(String stockName);
	
	Stock findByModelType(String modelType);
	
	Collection<Stock> findByusers_UsersId(long usersId);
	
	Collection<Stock> findAll();

	
}
