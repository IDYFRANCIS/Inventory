package com.bizzdesk.inventory.dto;

public class StockResponseDto {
	
	//private StockCategory categoryName;
	private String stockId;
	private String brandName;
	private String stockName;
	private String modelType;
	
	
//	public StockCategory getCategoryName() {
//		return categoryName;
//	}
//	public void setCategoryName(StockCategory categoryName) {
//		this.categoryName = categoryName;
//	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	
	
}
