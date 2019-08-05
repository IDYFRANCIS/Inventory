package com.bizzdesk.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bizzdesk.inventory.model.StockCategory;


@Repository
public interface CategoryRepository extends CrudRepository<StockCategory, String>{
	
	StockCategory findByCategoryName(String categoryName);
	
	StockCategory findByCategoryId(String categoryId);
	

}
