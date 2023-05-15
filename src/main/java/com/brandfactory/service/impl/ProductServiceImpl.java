package com.brandfactory.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.brandfactory.Exception.BusinessException;
import com.brandfactory.client.productMain;
import com.brandfactory.converter.ProductConverter;
import com.brandfactory.dto.ProductDTO;
import com.brandfactory.dto.CategoryDTO;
import com.brandfactory.entity.ProductEntity;
import com.brandfactory.repository.ProductRepository;
import com.brandfactory.repository.impl.ProductRepositoryImpl;
import com.brandfactory.service.ProductService;

public class ProductServiceImpl implements ProductService {

	List<ProductDTO> productList = new ArrayList<>();
	ProductRepository productRepository;
	
	public ProductServiceImpl()
	{
		productRepository = new ProductRepositoryImpl();
	}
	
	@Override
	public ProductDTO addProduct(ProductDTO productDTO) throws BusinessException {

		for (ProductDTO product : productList) {
			if (product.getProductName().equalsIgnoreCase(productDTO.getProductName())) {
				BusinessException exp = new BusinessException();
				exp.setErrorCode("DUP_PR_007");
				exp.setErrorMsg("Product Already Exists");
				throw exp;
			}
		}

		//DataSerializer.serializer(productDTO);
		// productList.add(productDTO);
		
		ProductEntity pe = ProductConverter.dTOToEntityConverter(productDTO);
		pe = productRepository.addProduct(pe);
		productDTO = ProductConverter.entityToDTOConverter(pe);
		System.out.println("Product got added Sucessfully");
		System.out.println("*****************************************");

		return productDTO;
	}

	@Override
	public List<ProductDTO> getAllProduct() {
		
		List<ProductEntity> productEntities = productRepository.getAllProduct();
		List<ProductDTO> productDTOs = new ArrayList<>();
		 
		for(ProductEntity pe : productEntities)
		{
			ProductDTO dto = ProductConverter.entityToDTOConverter(pe);
			productDTOs.add(dto);
		}
		
		return productDTOs;
	}

	
	@Override
	public ProductDTO updateProductPrice(Double newPrice, Integer pid) {

		getAllProduct();

		ProductEntity pe = productRepository.updateProductPrice(newPrice, pid);
		ProductDTO dto = ProductConverter.entityToDTOConverter(pe);
		
		return dto;
	}
	
	@Override
	public ProductDTO deleteProduct(Integer pid) {

		getAllProduct();
		
		ProductEntity pe = productRepository.deleteProduct(pid);
		ProductDTO dto = ProductConverter.entityToDTOConverter(pe);
		
		return dto;
	}

	
	/*
	 * @Override public ProductDTO updateProductPrice(Double newPrice, Integer pid)
	 * {
	 * 
	 * getAllProduct();
	 * 
	 * for (ProductDTO product : productList) { if (pid == product.getProductId()) {
	 * product.setPricePerQty(newPrice);
	 * 
	 * System.out.println("Product was updated Successfully...! "); return product;
	 * }
	 * 
	 * }
	 * 
	 * return null; }
	 */
	/*
	 * @Override public ProductDTO deleteProduct(Integer pid) {
	 * 
	 * getAllProduct(); Iterator<ProductDTO> iterator = productList.iterator();
	 * ProductDTO deleted = null; while (iterator.hasNext()) { ProductDTO dto =
	 * iterator.next();
	 * 
	 * if (dto.getProductId() == pid) { deleted = dto; iterator.remove();
	 * System.out.println("Product with id: " + pid + " has been Deleted"); }
	 * 
	 * }
	 * 
	 * return deleted; }
	 */
	@Override
	public ProductDTO getProductById(Integer pid) {

		getAllProduct();
		for (ProductDTO product : productList) {
			if (product.getProductId() == pid) {
				System.out.println(" ***@@@ Product Found @@@*** ");
				System.out.println(" productId :" + product.getProductId());
				System.out.println(" product Name :" + product.getProductName());
				System.out.println(" product Price :" + product.getPricePerQty());
				System.out.println(" product's AvailableQty :" + product.getAvailableQty());
				System.out.println(" product's Category :" + product.getCategoryDTO().getCategoryName());
				System.out.println("*************************************");

				return product;
			}
		}
		return null;
	}

	/*
	 * @Override public ProductDTO getProductById(long pid) {
	 * 
	 * ProductDTO product = DataSerializer.deserializer(pid);
	 * System.out.println(" ***@@@ Product Found @@@*** ");
	 * System.out.println(" productId :" + product.getProductId());
	 * System.out.println(" product Name :" + product.getProductName());
	 * System.out.println(" product Price :" + product.getPricePerQty());
	 * System.out.println(" product's AvailableQty :" + product.getAvailableQty());
	 * System.out.println(" product's Category :" +
	 * product.getCategpryDTO().getCategoryName());
	 * System.out.println("*************************************");
	 * 
	 * return product;
	 * 
	 * }
	 */

	@Override
	public List<ProductDTO> searchProductByName(String productName) {

		List<ProductDTO> foundProducts = new ArrayList<>();

		if (productName != null) {
			for (ProductDTO product : productList) {
				if (product.getProductName().contains(productName)) {

					System.out.println(" ***@@@ Product Found @@@*** ");
					System.out.println(" productId :" + product.getProductId());
					System.out.println(" product Name :" + product.getProductName());
					System.out.println(" product Price :" + product.getPricePerQty());
					System.out.println(" product's AvailableQty :" + product.getAvailableQty());
					System.out.println(" product's Category :" + product.getCategoryDTO().getCategoryName());
					System.out.println("*************************************");

					foundProducts.add(product);

				}
			}

			return foundProducts;
		}
		return null;
	}

}
