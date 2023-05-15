package com.brandfactory.client;

import java.security.Provider.Service;
import java.util.List;
import java.util.Locale.Category;
import java.util.Scanner;

import com.brandfactory.Exception.BusinessException;
import com.brandfactory.dto.CategoryDTO;
import com.brandfactory.dto.ProductDTO;
import com.brandfactory.service.ProductService;
import com.brandfactory.service.impl.ProductServiceImpl;

public class productMain {

	public static void main(String[] args) {

		ProductService service = new ProductServiceImpl();

		while (true) {

			createMenu();
			Scanner input = new Scanner(System.in);
			int choice = input.nextInt();

			if (choice == 1) {
				addProduct(service);
			} else if (choice == 2) {
				getAllProduct(service);
			} else if (choice == 3) {
				getProductById(service);
			} else if (choice == 4) {
				updateProductById(service);
			} else if (choice == 5) {
				searchProductByName(service);
			} else if (choice == 6) {
				deleteProduct(service);
			} else if (choice == 99) {
				System.out.println("Exiting the Application");
				System.exit(0);
			}
		}
	}

	private static void deleteProduct(ProductService service) {

		System.out.println("Enter the product id: ");
		Scanner sc = new Scanner(System.in);
		Integer id = sc.nextInt();
		
		ProductDTO product = service.deleteProduct(id);

		if (product != null) {

			System.out.println(" productId :" + product.getProductId());
			System.out.println(" product Name :" + product.getProductName());
			System.out.println(" product Price :" + product.getPricePerQty());
			System.out.println(" product's AvailableQty :" + product.getAvailableQty());
			System.out.println(" product's Category :" + product.getCategoryDTO().getCategoryName());

		} else {
			System.out.println("Product was not Found for the given product id: " + id);
		}

	}

	private static void searchProductByName(ProductService service) {

		System.out.println("Enter the productName to search: ");
		Scanner sc = new Scanner(System.in);
		String pName = sc.next();
		
		
		List<ProductDTO> productList = service.searchProductByName(pName);

		if (productList != null && !productList.isEmpty()) {
			for (ProductDTO product : productList) {

				System.out.println(" ***@@@ Product Found @@@*** ");
				System.out.println(" productId :" + product.getProductId());
				System.out.println(" product Name :" + product.getProductName());
				System.out.println(" product Price :" + product.getPricePerQty());
				System.out.println(" product's AvailableQty :" + product.getAvailableQty());
				System.out.println(" product's Category :" + product.getCategoryDTO().getCategoryName());
				System.out.println("*************************************");
			}
		} else {
			System.out.println("Product was not Found for the given product name: " + pName);
		}

	}

	private static void updateProductById(ProductService service) {

		System.out.println("Enter thIntegere product id to update: ");
		Scanner sc = new Scanner(System.in);
		Integer id = sc.nextInt();

		System.out.println("Enter the product's new Price to update: ");
		Scanner sc2 = new Scanner(System.in);
		Double newPrice = sc2.nextDouble();
		ProductDTO product = service.updateProductPrice(newPrice, id);

		if (product != null) {

			System.out.println(" productId :" + product.getProductId());
			System.out.println(" product Name :" + product.getProductName());
			System.out.println(" product Price :" + product.getPricePerQty());
			System.out.println(" product's AvailableQty :" + product.getAvailableQty());
			System.out.println(" product's Category :" + product.getCategoryDTO().getCategoryName());
		} else {
			System.out.println("Product was not Found for the given product name: " + id);
		}

	}

	private static void getProductById(ProductService service) {

		System.out.println("Enter the product id: ");
		Scanner sc = new Scanner(System.in);
		Integer id = sc.nextInt();

		ProductDTO product = service.getProductById(id);
		if (product == null) {
			System.out.println("Product was not Found for the given product id: " + id);
		}

	}

	private static void createMenu() {

		System.out.println("*****************************************");
		System.out.println("Enter 1 to add the product");
		System.out.println("Enter 2 to getAll the product");
		System.out.println("Enter 3 to get the product by Id");
		System.out.println("Enter 4 to update the product");
		System.out.println("Enter 5 to search the product by name");
		System.out.println("Enter 6 to delete the product");
		System.out.println("*****************************************");

	}

	private static void getAllProduct(ProductService service) {

		List<ProductDTO> list = service.getAllProduct();

		System.out.println("Displayng All the Products...");
		
		for (ProductDTO dto : list) {
			
			System.out.println(" productId :" + dto.getProductId());
			System.out.println(" product Name :" + dto.getProductName());
			System.out.println(" product Price :" + dto.getPricePerQty());
			System.out.println(" product's AvailableQty :" + dto.getAvailableQty());
			System.out.println(" product's Category :" + dto.getCategoryDTO().getCategoryName());
			System.out.println("*****************************************");
		}

	}

	public static void addProduct(ProductService service) {

		ProductDTO dto = new ProductDTO();
		Scanner sc = new Scanner(System.in);

		//dto.setProductId(System.currentTimeMillis());

		System.out.println("Enter the product Name: ");
		String name = sc.next();
		dto.setProductName(name);
		
		sc.nextLine();
		
		System.out.println("Enter the product Description: ");
		String desc = sc.nextLine();
		dto.setProductDesc(desc);
		
		
		System.out.println("Enter the product Price: ");
		Double price = sc.nextDouble();
		dto.setPricePerQty(price);

		System.out.println("Enter the product AvailableQty: ");
		Integer availableQty = sc.nextInt();
		dto.setAvailableQty(availableQty);

		/*
		 * System.out.println("Enter the product Category Name: ");
		 * 
		 * CategoryDTO category = new CategoryDTO();
		 * category.setCategoryName(sc.next());
		 */
		
		 CategoryDTO category = new CategoryDTO();
		 System.out.println("Enter the product Category id: ");
		 category.setCategoryId(sc.nextInt());
		 
		dto.setCategoryDTO(category);

		
		try {
			service.addProduct(dto);
		} catch (BusinessException e) {
			
			System.out.println(e.getErrorMsg());
			System.out.println(e.getErrorCode() );
			
		}

	}
}
