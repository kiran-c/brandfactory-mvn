package com.brandfactory.service;

import java.util.List;

import com.brandfactory.Exception.BusinessException;
import com.brandfactory.dto.ProductDTO;

public interface ProductService {
	
	public ProductDTO addProduct(ProductDTO productDTO) throws BusinessException;
	public ProductDTO getProductById(Integer pid);
	public List<ProductDTO> getAllProduct();
	public ProductDTO updateProductPrice(Double newPrice, Integer pid);
	public ProductDTO deleteProduct(Integer pid);
	public List<ProductDTO>searchProductByName(String productName);
	
}
