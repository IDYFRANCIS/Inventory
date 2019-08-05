package com.bizzdesk.inventory.service.impl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizzdesk.inventory.constant.ServerResponseStatus;
import com.bizzdesk.inventory.dto.CategoryDto;
import com.bizzdesk.inventory.dto.ServerResponse;
import com.bizzdesk.inventory.model.Stock;
import com.bizzdesk.inventory.model.StockCategory;
import com.bizzdesk.inventory.model.Users;
import com.bizzdesk.inventory.repository.CategoryRepository;
import com.bizzdesk.inventory.repository.StockRepository;
import com.bizzdesk.inventory.service.CategoryService;


@Transactional
@Service
public class CategoryImpl implements CategoryService{

	@Autowired
	StockRepository stockRepo;
	
	@Autowired
	CategoryRepository catgeoryRepo;

	
	
	@Override
	public Collection<StockCategory> findAll() {
		
		try {
			return (Collection<StockCategory>) catgeoryRepo.findAll();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	@Override
	public StockCategory findByCategoryId(String categoryId) {
		
		try {
			return catgeoryRepo.findByCategoryId(categoryId);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	@Override
	public StockCategory findByCategoryName(String categoryName) {
		
		try {
			return catgeoryRepo.findByCategoryName(categoryName);
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return null;
	}

	
	
	@Override
	public ServerResponse create(CategoryDto category) {
		
		ServerResponse response = new ServerResponse();
		
		StockCategory category1 = null;
		
		 String categoryName = category.getCategoryName() != null ? category.getCategoryName() : category.getCategoryName();
		
		 if (categoryName == null || categoryName.isEmpty()) {
				
				response.setData("");
				response.setMessage("Please enter category name");
				response.setSuccess(false);
				response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
		 
		 try {
				
				StockCategory requestCategory = catgeoryRepo.findByCategoryName(categoryName);
				
				if(requestCategory != null) {
					response.setData("");
					response.setMessage("Category already exist");
	            	response.setSuccess(false);
					response.setStatus(ServerResponseStatus.FAILED);
					
					return response;
				}
				
				category1 = new StockCategory();
				
				category1.setCategoryName(categoryName);
				
				catgeoryRepo.save(category1);
		 
				    response.setData(category);
					response.setMessage("Category created successfully");
					response.setSuccess(true);
					response.setStatus(ServerResponseStatus.CREATED);
				
				} catch (Exception e) {
					
					response.setData("");
					response.setMessage("Failed to create category");
					response.setSuccess(false);
					response.setStatus(ServerResponseStatus.FAILED);
					e.printStackTrace();
					return response;
				}
			
				return response;
	}

	
	

	@Override
	public ServerResponse viewAll() {
		
       ServerResponse response = new ServerResponse();
		
		try {
			Collection<StockCategory> stock = findAll();
			
			if (stock.size() < 1) {
				response.setData("");
				response.setMessage("StockCategory list is empty");
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
	public ServerResponse delete(String categoryId) {
		
		
		 ServerResponse response = new ServerResponse();
			
			if (categoryId == null || categoryId.isEmpty()) {
				
				response.setData("");
				response.setMessage("Category details cannot be empty");
				response.setStatus(ServerResponseStatus.FAILED);
				response.setSuccess(false);
					
				return response;
			}
			
			try {
				
				StockCategory category = catgeoryRepo.findByCategoryId(categoryId);
				
				
				if (category == null) {
					response.setData("Category not found");
					response.setStatus(ServerResponseStatus.FAILED);
					response.setSuccess(false);
					
					return response;
				}
				
				catgeoryRepo.delete(category);
				
				 response.setStatus(ServerResponseStatus.DELETED);
				 response.setData("Category has been successfully deleted");
				 response.setSuccess(true);

		        } catch (Exception e) {
		        	response.setStatus(ServerResponseStatus.FAILED);
		        	response.setData("Failed to delete stock category");
		        	response.setSuccess(false);
		            e.printStackTrace();
		            return response;
		        }
			
			return response;
			
		}
			

}
