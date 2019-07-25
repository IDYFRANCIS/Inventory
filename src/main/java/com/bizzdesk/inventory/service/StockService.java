package com.bizzdesk.inventory.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.bizzdesk.inventory.dto.ServerResponse;
import com.bizzdesk.inventory.dto.StockDto;
import com.bizzdesk.inventory.dto.UpdateStockDto;
import com.bizzdesk.inventory.model.Stock;


@Service
public interface StockService {
	
	public Collection<Stock> findAll();
	
	public Stock findByStockId(String stockId);
	
	ServerResponse create(StockDto request); 
	
	ServerResponse update(String stockId, UpdateStockDto request);
	
	ServerResponse assignStockToUser(String stockId, long usersId);
	
	ServerResponse getStock(String stockId);
	
	ServerResponse viewAll();
	
	

//	ServerResponse update(String stockId, StockDto request);

}
