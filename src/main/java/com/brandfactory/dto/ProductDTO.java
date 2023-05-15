package com.brandfactory.dto;

import java.io.Serializable;

public class ProductDTO implements Serializable {

	private Integer productId;
	private String productName;
	private String productDesc;
	private Double pricePerQty;
	private Integer availableQty;
	private CategoryDTO categoryDTO;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	
	}
	public Double getPricePerQty() {
		return pricePerQty;
	}
	public void setPricePerQty(Double pricePerQty) {
		this.pricePerQty = pricePerQty;
	}
	public Integer getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(Integer availableQty) {
		this.availableQty = availableQty;
	}
	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}
	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}
}
