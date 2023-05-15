package com.brandfactory.serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.brandfactory.dto.ProductDTO;

public class DataSerializer {

	public static void serializer(ProductDTO productDTO)
	{
		File file = new File(productDTO.getProductId()+".ser");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(productDTO);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		 }
		
		finally
		{
			try {
				oos.close();
				fos.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public static ProductDTO deserializer(long id)
	{
		File file = new File(id+".ser");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		ProductDTO product = null;;
		try {
			
			fis = new FileInputStream(file);
			ois  = new ObjectInputStream(fis);
			product = (ProductDTO) ois.readObject();
		}
		 catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		catch (IOException e) {
			e.printStackTrace();
		 }
		
		finally
		{
			try {
				ois.close();
				fis.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	
		return product;
	}
}
