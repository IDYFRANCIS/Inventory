package com.bizzdesk.inventory.service.impl;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizzdesk.inventory.constant.ServerResponseStatus;
import com.bizzdesk.inventory.dto.ServerResponse;
import com.bizzdesk.inventory.dto.StockDto;
import com.bizzdesk.inventory.dto.StockResponseDto;
import com.bizzdesk.inventory.dto.UpdateStockDto;
import com.bizzdesk.inventory.model.Stock;
import com.bizzdesk.inventory.model.StockCategory;
import com.bizzdesk.inventory.model.Users;
import com.bizzdesk.inventory.repository.CategoryRepository;
import com.bizzdesk.inventory.repository.StockRepository;
import com.bizzdesk.inventory.repository.UsersRepository;
import com.bizzdesk.inventory.service.StockService;


@Transactional
@Service
public class StockServiceImpl implements StockService{

	@Autowired
	UsersRepository usersRepo;
	
	@Autowired
	StockRepository stockRepo;
	
	@Autowired
	CategoryRepository categoryRepo;
	
	
	@Override
	public Stock findByStockId(String stockId) {
		
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
	public Stock findByBrandName(String brandName) {
		
		try {
			return stockRepo.findByBrandName(brandName);
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		
		return null;
	}

	
	@Override
	public Stock findByModelType(String modelType) {
		
		try {
			return stockRepo.findByModelType(modelType);
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public Collection<Stock> findByCategory(String categoryId) {
		
		try {
			return (Collection<Stock>) stockRepo.findByStockCategory_CategoryId(categoryId);
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ServerResponse create(StockDto request) {
		
		ServerResponse response = new ServerResponse();
		
       Stock stock = null;
		
        String categoryId = request.getCategoryId() != null ? request.getCategoryId() : request.getCategoryId()
;		String stockName = request.getStockName() != null ? request.getStockName() : request.getStockName();
		String brandName = request.getBrandName() != null ? request.getBrandName() : request.getBrandName();
		String modelType = request.getModelType() != null ? request.getModelType() : request.getModelType();
		
		
      if (categoryId == null || categoryId.isEmpty()) {
			
    		response.setData("");
			response.setMessage("Please enter category Id");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
		
		
		if (stockName == null || stockName.isEmpty()) {
			
			response.setData("");
			response.setMessage("Please enter stock name");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
		
		if (brandName == null  ||  brandName.isEmpty() ) {
			
			response.setData("");
			response.setMessage("Please  provide brand name");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			
			return  response;
		}
		
       if (modelType == null || modelType.isEmpty()) {
			
			response.setData("");
			response.setMessage("Please enter model type");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
		
		try {
			
			StockCategory category = categoryRepo.findByCategoryId(categoryId);
			
			if(category == null) {
				response.setData("");
				response.setMessage("category not found");
				response.setSuccess(false);
				response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
		
			
			Stock requestStock = stockRepo.findByStockName(stockName);
			
			if(requestStock != null) {
				response.setData("");
				response.setMessage("Stock already exist");
            	response.setSuccess(false);
				response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
		
			
		 stock =  new Stock();
		 
		 stock.setStockName(stockName);
		 stock.setBrandName(brandName);
		 stock.setModelType(modelType);
		 stock.setStockCategory(category);
		 
		 stockRepo.save(stock);
		 
		    response.setData(stock);
			response.setMessage("Stock created successfully");
			response.setSuccess(true);
			response.setStatus(ServerResponseStatus.UPDATED);
		
		} catch (Exception e) {
			
			response.setData("");
			response.setMessage("Failed to create stcok");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			e.printStackTrace();
			return response;
		}
	
		return response;
	}

	@Override
	public ServerResponse update(String stockId, UpdateStockDto request) {
		
		ServerResponse response = new ServerResponse();
		
		Stock stock = null;
		
		String stockName = request.getStockName() != null ? request.getStockName() : request.getStockName();
		String brandName = request.getBrandName() != null ? request.getBrandName() : request.getBrandName();
		String modelType = request.getModelType() != null ? request.getModelType() : request.getModelType();
		
		if (stockName == null) {
			
			response.setData("");
			response.setMessage("Please provide stock name");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
		
      if (brandName == null) {
			
			response.setData("");
			response.setMessage("Please provide brand name");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
      
      if (modelType == null) {
			
			response.setData("");
			response.setMessage("Please provide model type");
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
			if (brandName != null)
				stock.setBrandName(brandName);
			if(modelType != null)
				stock.setModelType(modelType);
			
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
			return response;
		}
		
		return response;
	}

	@Override
	public ServerResponse assignStockToUser(String stockId, long usersId) {
		
		ServerResponse response = new ServerResponse();
		
		
       if (stockId == null || stockId.isEmpty()) {
			
			response.setData("");
			response.setMessage("Please provide stock details");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
		
		if (usersId == 0) {
			
			response.setData("");
			response.setMessage("Please provide user details");
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
			stock.setUsers(users);
			
			response.setData(users);
			response.setMessage("Stock assigned successfully to user");
			response.setSuccess(true);
			response.setStatus(ServerResponseStatus.OK);
			
		}catch(Exception e) {
			response.setData("");
			response.setMessage("Failed to assign stock");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
		
		return response;
	}

	@Override
	public ServerResponse getStock(String stockId) {
		
		ServerResponse response = new ServerResponse();
		
		if(stockId == null || stockId.isEmpty()) {
			response.setData("");
			response.setMessage(" Please enter Stock details");
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
			return response;
		}
		
		
		return response;
	}


	@Override
	public ServerResponse viewAll() {
		
		ServerResponse response = new ServerResponse();
		
		try {
			Collection<Stock> stock = stockRepo.findAll();
			
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
			return response;
		}
		
		return response;
	}


	@Override
	public ServerResponse getStockByCategory(String categoryId) {
		
       ServerResponse response = new ServerResponse();
		
		if(categoryId == null || categoryId.isEmpty()) {
			response.setData("");
			response.setMessage("Please enter Stock Category details");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
		
		try {
			
			Collection<Stock> catStock = stockRepo.findByStockCategory_CategoryId(categoryId);
			
			if (catStock.size() < 1) {
				response.setData("");
				response.setMessage("No stock found under this category");
				response.setSuccess(false);
				response.setStatus(ServerResponseStatus.NO_CONTENT);
				
				return response;
				
			}
			
			
			Collection<StockResponseDto> dtos = new HashSet<>();
			
			for(Stock stocks: catStock) {
				StockResponseDto dto = new StockResponseDto();
				dto.setBrandName(stocks.getBrandName());
				dto.setModelType(stocks.getModelType());
				dto.setStockName(stocks.getStockName());
				dto.setStockId(stocks.getStockId());
				//dto.setCategoryName(stocks.getStockCategory());
				dtos.add(dto);
				
			}
			
			response.setData(dtos);
			response.setMessage("Stock found successfully");
			response.setSuccess(true);
			response.setStatus(ServerResponseStatus.OK);
		//	System.out.println("this is the stock" + dtos);
			
		} catch (Exception e){
			
			response.setData("");
			response.setMessage("Failed to fetch stock");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
			return response;
		}
		
		
		return response;
	}

	
}
