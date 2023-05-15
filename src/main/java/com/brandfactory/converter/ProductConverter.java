package com.brandfactory.converter;

import com.brandfactory.dto.CategoryDTO;
import com.brandfactory.dto.ProductDTO;
import com.brandfactory.entity.CategoryEntity;
import com.brandfactory.entity.ProductEntity;

public class ProductConverter {

	
	public static ProductDTO entityToDTOConverter(ProductEntity productEntity)
	{
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(productEntity.getProductId());
		productDTO.setProductName(productEntity.getProductName());
		productDTO.setProductDesc(productEntity.getProductDesc());
		productDTO.setPricePerQty(productEntity.getPricePerQty());
		productDTO.setAvailableQty(productEntity.getAvailableQty());
		
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(productEntity.getCategoryEntity().getCategoryId());
		categoryDTO.setCategoryName(productEntity.getCategoryEntity().getCategoryName());
		categoryDTO.setCategoryDesc(productEntity.getCategoryEntity().getCategoryDesc());
		
		productDTO.setCategoryDTO(categoryDTO);
		
		
		return productDTO;
	}
	
	public static ProductEntity dTOToEntityConverter(ProductDTO productDTO)
	{
		
		ProductEntity productEntity = new ProductEntity();
		productEntity.setProductName(productDTO.getProductName());
		productEntity.setProductDesc(productDTO.getProductDesc());
		productEntity.setPricePerQty(productDTO.getPricePerQty());
		productEntity.setAvailableQty(productDTO.getAvailableQty());
		
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setCategoryId(productDTO.getCategoryDTO().getCategoryId());
		categoryEntity.setCategoryName(productDTO.getCategoryDTO().getCategoryName());
		categoryEntity.setCategoryDesc(productDTO.getCategoryDTO().getCategoryDesc());
		
		productEntity.setCategoryEntity(categoryEntity);
		return productEntity;
	}
	
}
