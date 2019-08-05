package com.bizzdesk.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizzdesk.inventory.constant.ServerResponseStatus;
import com.bizzdesk.inventory.dto.CategoryDto;
import com.bizzdesk.inventory.dto.ServerResponse;
import com.bizzdesk.inventory.dto.StockDto;
import com.bizzdesk.inventory.dto.UpdateStockDto;
import com.bizzdesk.inventory.service.StockService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;





@Api(tags = "Stock Management", description = "Endpoint")
@RequestMapping(value = "/stock", produces = "application/json")
@Controller
public class StockController {
	
	@Autowired
	StockService stockService;
	
	private HttpHeaders responseHeaders = new HttpHeaders();
	
	/**
	 * CREATE STOCK
	 * @param authorization
	 * @param request
	 * @return
	 */
	
	@ApiOperation(value = "Create a stock", response = ServerResponse.class)
	@RequestMapping(value = "/create-stock", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> create(@RequestHeader("Authorization")  String authorization, @RequestBody StockDto request){
		
		ServerResponse response = new ServerResponse();
		
		try {
			
			response = stockService.create(request);
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
            response.setMessage("Failed to create stock");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            
		}
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}

	/**
	 * VIEW ALL STOCK
	 * @param authorization
	 * @return
	 */
	
	@ApiOperation(value = "Get all stock", response = ServerResponse.class)
    @RequestMapping(value = "/get-all-stock", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> viewAll(@RequestHeader("Authorization") String authorization){
		
		ServerResponse response = new ServerResponse();
		
		try {
			System.out.println("ID testing");
			response = stockService.viewAll();
		
		} catch (Exception e) {
			e.printStackTrace();
			response.setData("An error occured while fetching stocks" + e.getMessage());
			response.setMessage("An error occured while fetching stocks");
	        response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}
	
	
	/**
	 * UPDATE STOCK
	 * @param authorization
	 * @param stockId
	 * @param request
	 * @return
	 */
	
	@ApiOperation(value = "Update stock", response = ServerResponse.class)
	@RequestMapping(value = "/update-stock/{stockId}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> update(@RequestHeader("Authorization") String authorization, @PathVariable("stockId") String stockId, @RequestBody UpdateStockDto request){
		
		ServerResponse response = new ServerResponse();
		
		try {
			response = stockService.update(stockId, request);
			
		} catch (Exception e) {
			response.setData("An error occured => " + e.getMessage());
			response.setMessage("Failed to update stock details");
			response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	

	
	/**
	 * ASSIGN STOCK TO A USER
	 * @param authorization
	 * @param stockId
	 * @param usersId
	 * @return
	 */
	@ApiOperation(value = "Assign stock to user", response = ServerResponse.class)
	@RequestMapping(value = "/assign-stock-to-user/{stockId}/{usersId}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> assignStock(@RequestHeader("Authorization") String authorization, @PathVariable("stockId") String stockId, @PathVariable("usersId") Long usersId ){
		
		ServerResponse response = new ServerResponse();
		
		try {
			response = stockService.assignStockToUser(stockId, usersId);
			
		} catch (Exception e) {
			response.setData("An error occured => " + e.getMessage());
			response.setMessage("Failed to assign stock to user");
			response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	
	
	
	/**
	 * GET STOCK BY ID
	 * @param authorization
	 * @param stockId
	 * @return
	 */
	
	@ApiOperation(value = "Get stock by ID", response = ServerResponse.class)
	@RequestMapping(value = "/get-stock/{stockId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getStock(@RequestHeader("Authorization") String authorization, @PathVariable("stockId") String stockId){
		
		ServerResponse response = new ServerResponse();
		
		try {
			response = stockService.getStock(stockId);
			
		} catch (Exception e) {
			response.setData("An error occured => " + e.getMessage());
			response.setMessage("Failed to fetch stock details");
			response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	
	
}
