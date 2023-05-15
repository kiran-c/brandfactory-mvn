package com.brandfactory.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.brandfactory.Exception.BusinessException;
import com.brandfactory.configuration.DBConfiguration;
import com.brandfactory.entity.CategoryEntity;
import com.brandfactory.entity.ProductEntity;
import com.brandfactory.repository.ProductRepository;



public class ProductRepositoryImpl implements ProductRepository {

	@Override
	public ProductEntity addProduct(ProductEntity productEntity) throws BusinessException {
		
		Connection conn = DBConfiguration.getDBConnection();
		String sql = "INSERT INTO Product (product_name, product_description, price_per_qty, available_qty, category_id_fk) VALUES (?, ?, ?, ?, ?)";
		
		try {
		
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, productEntity.getProductName());
			statement.setString(2, productEntity.getProductDesc());
			statement.setDouble(3, productEntity.getPricePerQty());
			statement.setInt(4, productEntity.getAvailableQty());
			statement.setInt(5, productEntity.getCategoryEntity().getCategoryId());
			int resultSet = statement.executeUpdate();
			
			if(resultSet>0)
			{
				System.out.println("Rows inserted Successfully...!");
			}
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		
		return productEntity;
	}

	@Override
	public ProductEntity getProductById(long pid) {
		
		return null;
	}

	@Override
	public List<ProductEntity> getAllProduct() {
		
		Connection conn = DBConfiguration.getDBConnection();
		
		String sql = "SELECT p.product_id, p.product_name, p.product_description, p.price_per_qty, p.available_qty, c.category_id, c.category_name\r\n"
				+ "FROM product p, category c WHERE p.category_id_fk = c.category_id";

		List<ProductEntity> productList = new ArrayList<>();
		
		try {
	
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				Integer productId = resultSet.getInt("product_id");
				String productName = resultSet.getString("product_name");
				String productDesc = resultSet.getString("product_description");
				Double pricePerQty = resultSet.getDouble("price_per_qty");
				Integer availableQty = resultSet.getInt("available_qty");
				Integer categoryId = resultSet.getInt("category_id");
				String categoryName = resultSet.getString("category_name");
				
				ProductEntity pe = new ProductEntity();
				pe.setProductId(productId);
				pe.setProductName(productName);
				pe.setProductDesc(productDesc);
				pe.setPricePerQty(pricePerQty);
				pe.setAvailableQty(availableQty);
				
				CategoryEntity ce = new CategoryEntity();
				ce.setCategoryId(categoryId);
				ce.setCategoryName(categoryName);
				pe.setCategoryEntity(ce);
				
				productList.add(pe);
				
			}
			
	
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return productList;
	}

	@Override
	public ProductEntity updateProductPrice(Double newPrice, Integer pid) {
		
		Connection conn = DBConfiguration.getDBConnection();
		
		String sql = "UPDATE product SET price_per_qty= ? WHERE product_id = ?";
		 ProductEntity pe = null;
		
		try {
		
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, newPrice);
			statement.setInt(2, pid);
			
			int resultSet = statement.executeUpdate();
			
			if(resultSet > 0)
			{
				System.out.println("Rows updated successfully...");
				
				 sql = "SELECT p.product_id, p.product_name, p.product_description, p.price_per_qty, p.available_qty, c.category_id, c.category_name\r\n"
						+ "FROM product p, category c WHERE p.product_id = ?";
				 
				 statement = conn.prepareStatement(sql);
				 statement.setInt(1, pid);
				 
				 ResultSet result = statement.executeQuery();
				 
				 while(result.next())
				 {
					  Integer prid = result.getInt("product_id");
					  String pName = result.getString("product_name");
					  String pDesc = result.getString("product_description");
					  Double ppq = result.getDouble("price_per_qty");
					  Integer av_qty = result.getInt("available_qty"); 
					  Integer cid = result.getInt("category_id");
					  String cname= result.getString("category_name");
					  
					  
					 pe = new ProductEntity();
					  pe.setProductId(prid);
					  pe.setProductName(pName);
					  pe.setProductDesc(pDesc);
					  pe.setPricePerQty(ppq);
					  pe.setAvailableQty(av_qty);
					  
					  CategoryEntity ce = new CategoryEntity();
					  ce.setCategoryId(cid);
					  ce.setCategoryName(cname);
					  pe.setCategoryEntity(ce);
					  
					  
				 }
			}
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return pe;
	}

	@Override
	public ProductEntity deleteProduct(Integer pid) {
		
		
		
		Connection conn = DBConfiguration.getDBConnection();
		
		//String sql = "SELECT * FROM `product` WHERE `product_id` =  ? ";
		
		String sql = "SELECT p.product_id, p.product_name, p.product_description, p.price_per_qty, p.available_qty, c.category_id, c.category_name\r\n"
				 + "FROM product p, category c WHERE p.category_id_fk = c.category_id AND p.product_id =  ? ";
	
		ProductEntity pe = null;
		
		try {
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, pid);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next())			{
				
				Integer productId = resultSet.getInt("product_id");
				String productName = resultSet.getString("product_name");
				String productDesc = resultSet.getString("product_description");
				Double pricePerQty = resultSet.getDouble("price_per_qty");
				Integer availableQty = resultSet.getInt("available_qty");
				Integer categoryId = resultSet.getInt("category_id");
				String categoryName = resultSet.getString("category_name");
				
				pe = new ProductEntity();
				pe.setProductId(productId);
				pe.setProductName(productName);
				pe.setProductDesc(productDesc);
				pe.setPricePerQty(pricePerQty);
				pe.setAvailableQty(availableQty);
				
				CategoryEntity ce = new CategoryEntity();
				ce.setCategoryId(categoryId);
				ce.setCategoryName(categoryName);
				pe.setCategoryEntity(ce);
				
			
			}
			
			if(pe != null)
			{
				
				 sql = "DELETE FROM `product` WHERE `product_id` =  ? "; 
				statement = conn.prepareStatement(sql);
				statement.setInt(1, pid);
					
				int row = statement.executeUpdate();
				
				
				if(row > 0)
				{
					System.out.println("product with id "+pid+" got deleted successfully");
				}
			}
			else
			{
				System.out.println("product with id "+pid+" has not found");
			
			}
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		return pe;
	}

	@Override
	public List<ProductEntity> searchProductByName(String productName) {
		// TODO Auto-generated method stub
		return null;
	}

}
