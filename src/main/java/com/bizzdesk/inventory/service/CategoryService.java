package com.bizzdesk.inventory.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.bizzdesk.inventory.dto.CategoryDto;
import com.bizzdesk.inventory.dto.ServerResponse;
import com.bizzdesk.inventory.model.StockCategory;


@Service
public interface CategoryService {

	public Collection<StockCategory> findAll();
	
	public  StockCategory findByCategoryId(String categoryId);
	
	public StockCategory findByCategoryName(String categoryName);
	
	ServerResponse create(CategoryDto category);
	
	ServerResponse viewAll();
	
	ServerResponse delete(String categoryId);
	
}
