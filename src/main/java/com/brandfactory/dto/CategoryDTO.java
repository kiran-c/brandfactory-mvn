package com.brandfactory.dto;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
	
	private Integer categoryId;
	private String categoryName;
	private String categoryDesc;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer i) {
		this.categoryId = i;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		categoryDesc = categoryDesc;
	}
}
