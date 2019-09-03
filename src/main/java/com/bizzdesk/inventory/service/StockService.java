package com.bizzdesk.inventory.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.bizzdesk.inventory.dto.CategoryDto;
import com.bizzdesk.inventory.dto.ServerResponse;
import com.bizzdesk.inventory.dto.StockDto;
import com.bizzdesk.inventory.dto.UpdateStockDto;
import com.bizzdesk.inventory.model.Stock;


@Service
public interface StockService {
	
	public Collection<Stock> findAll();
	
	public Stock findByStockId(String stockId);
	
	public Stock findByBrandName(String brandName);
	
	public Stock findByModelType(String modelType);
	
	public Collection<Stock> findByCategory(String categoryId);
	
	ServerResponse create(StockDto request); 
	
	ServerResponse update(String stockId, UpdateStockDto request);
	
	ServerResponse assignStockToUser(String stockId, long usersId);
	
	ServerResponse getStock(String stockId);
	
	ServerResponse viewAll();
	
	ServerResponse getStockByCategory(String categoryId);

}
