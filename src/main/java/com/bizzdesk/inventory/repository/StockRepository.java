package com.bizzdesk.inventory.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bizzdesk.inventory.model.Stock;



@Repository
public interface StockRepository extends CrudRepository<Stock, Long>{
	
	Stock findByStockId(String stockId);
	
	Stock findByStockLocation(String stockLocation);
	
	Stock findByStockName(String stockName);
	
	Collection<Stock> findByusers_UsersId(long usersId);
	
	Collection<Stock> findAll();

	
}
