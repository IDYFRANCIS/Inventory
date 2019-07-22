package com.bizzdesk.inventory.service.impl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizzdesk.inventory.constant.ServerResponseStatus;
import com.bizzdesk.inventory.dto.ServerResponse;
import com.bizzdesk.inventory.dto.StockDto;
import com.bizzdesk.inventory.dto.UpdateStockDto;
import com.bizzdesk.inventory.model.Stock;
import com.bizzdesk.inventory.model.Users;
import com.bizzdesk.inventory.repository.StockRepository;
import com.bizzdesk.inventory.repository.UsersRepository;
import com.bizzdesk.inventory.service.StockService;


@Service
@Transactional
public class StockServiceImpl implements StockService{

	@Autowired
	UsersRepository usersRepo;
	
	@Autowired
	StockRepository stockRepo;
	
	
	
	@Override
	public Stock findById(String stockId) {
		
		try {
			return stockRepo.findByStockId(stockId);
			
		} catch(Exception e){
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@Override
	public Collection<Stock> findAll() {
		
		try {
			return stockRepo.findAll();
					
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	@Override
	public ServerResponse create(StockDto request) {
		
		ServerResponse response = new ServerResponse();
		
		Stock stock = null;
		
		String stockName = request.getStockName() != null ? request.getStockName() : request.getStockName();
		String stockLocation = request.getStockLocation() != null ? request.getStockLocation() : request.getStockLocation();
		
		if (stockName == null || stockName.isEmpty()) {
			
			response.setData("");
			response.setMessage("Please enter stock name");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
		
		if (stockLocation == null  ||  stockLocation.isEmpty() ) {
			
			response.setData("");
			response.setMessage("Please  provide stock location");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			
			return  response;
		}
		
		try {
			
			Stock requestStock = stockRepo.findByStockId(stockName);
			
			if(requestStock != null) {
				response.setData("");
				response.setMessage("Stock already exist");
				response.setSuccess(false);
				response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
			
			Stock requestStockLoc = stockRepo.findByStockLocation(stockLocation);
			
			if(requestStockLoc != null) {
				response.setData("");
				response.setMessage("Stock already exist");
				response.setSuccess(false);
				response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
			
		 stock =  new Stock();
		 
		 stock.setStockName(stockName);
		 stock.setStockLocation(stockLocation);
		 
		 stockRepo.save(stock);
		 
		    response.setData(stock);
			response.setMessage("Stock created successfully");
			response.setSuccess(true);
			response.setStatus(ServerResponseStatus.UPDATED);
			
			return response;
		
		} catch (Exception e) {
			
			response.setData("");
			response.setMessage("Failed to create stcok");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			e.printStackTrace();
		}
	
		return response;
	}

	@Override
	public ServerResponse update(String stockId, UpdateStockDto request) {
		
		ServerResponse response = new ServerResponse();
		
		Stock stock = null;
		
		String stockName = request.getStockName() != null ? request.getStockName() : request.getStockName();
		String stockLocation = request.getStockLocation() != null ? request.getStockLocation() : request.getStockLocation();
		
		if (stockName == null) {
			
			response.setData("");
			response.setMessage("Please provide stock name");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
		
      if (stockLocation == null) {
			
			response.setData("");
			response.setMessage("Please provide stock location");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
		
		try {
			
			Stock stockUpdate = stockRepo.findByStockId(stockId);
			
			if (stockUpdate == null) {
				response.setData("");
				response.setMessage("Stock does not exist");
				response.setSuccess(false);
				response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
			
			stock = stockRepo.findByStockId(stockId);
			
			if (stockName != null)
				stock.setStockName(stockName);
			if (stockLocation != null)
				stock.setStockLocation(stockLocation);
			
			stockRepo.save(stock);
			
			response.setData(stock);
			response.setMessage("Stock updated successfully");
			response.setSuccess(true);
			response.setStatus(ServerResponseStatus.UPDATED);
			
		} catch (Exception e) {
			
			response.setData("");
			response.setMessage("Failed to update stock");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			e.printStackTrace();
		}
		
		return response;
	}

	@Override
	public ServerResponse assignStockToUser(String stockId, long usersId) {
		
		ServerResponse response = new ServerResponse();
		
		
		if (usersId == 0 || stockId == null) {
			
			response.setData("");
			response.setMessage("Please provide user or stock details");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
		
		try {
			Users users =  usersRepo.findByUsersId(usersId);
			 
			if (users == null) {
				response.setData("");
				response.setMessage("User not found");
				response.setSuccess(false);
				response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
			
			Stock stock = stockRepo.findByStockId(stockId);
			
			if (stock == null) {
				response.setData("");
				response.setMessage("Stock not found");
				response.setSuccess(false);
				response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
			
			users.getStock().add(stock);
			
			response.setData(stock);
			response.setMessage("Stock assigned successfully");
			response.setSuccess(true);
			response.setStatus(ServerResponseStatus.OK);
			
			return response;
			
		}catch(Exception e) {
			response.setData("");
			response.setMessage("Failed to assign stock");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}

	@Override
	public ServerResponse getStock(String stockId) {
		
		ServerResponse response = new ServerResponse();
		
		if(stockId == null || stockId.isEmpty()) {
			response.setData("");
			response.setMessage("Stock does not exist");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
		
		try {
			
			Stock stock = stockRepo.findByStockId(stockId);
			
			if (stock == null) {
				response.setData("");
				response.setMessage("Stock not found");
				response.setSuccess(false);
				response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
				
			}
			
			response.setData(stock);
			response.setMessage("Stock found successfully");
			response.setSuccess(true);
			response.setStatus(ServerResponseStatus.OK);
			
		} catch (Exception e){
			
			response.setData("");
			response.setMessage("Failed to fetch stock");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return response;
	}


	@Override
	public ServerResponse viewAll() {
		
		ServerResponse response = new ServerResponse();
		
		try {
			Collection<Stock> stock = findAll();
			
			if (stock.size() < 1) {
				response.setData("");
				response.setMessage("Stock list is empty");
				response.setSuccess(false);
				response.setStatus(ServerResponseStatus.NO_CONTENT);
				
				return response;
			}
			
			response.setData(stock);
			response.setMessage("Data fetched successfully");
			response.setSuccess(true);
			response.setStatus(ServerResponseStatus.OK);
		} catch (Exception e){
			
			response.setData("");
			response.setMessage("Failed to fetch data");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return response;
	}

	
}
