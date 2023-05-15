package com.brandfactory.repository;

import java.util.List;

import com.brandfactory.Exception.BusinessException;
import com.brandfactory.dto.ProductDTO;
import com.brandfactory.entity.ProductEntity;

public interface ProductRepository {

	public ProductEntity addProduct(ProductEntity productEntity) throws BusinessException;
	public ProductEntity getProductById(long pid);
	public List<ProductEntity> getAllProduct();
	public ProductEntity updateProductPrice(Double newPrice, Integer pid);
	public ProductEntity deleteProduct(Integer pid);
	public List<ProductEntity>searchProductByName(String productName);
	
}
