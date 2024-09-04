package com.productapp.service;

import java.time.LocalDate;
import java.util.List;

import com.productapp.exception.ProductNotFoundException;
import com.productapp.model.Product;

public interface IProductService {
    //CRUD OPERATION
	
	void addProduct(Product product);
	void updateProduct(int productId,double price);
	void deleteProduct(int productId);
	
	List<Product> getAll();
	List<Product>  getByBrand(String brand) throws ProductNotFoundException;
	List<Product>  getByCategory(String category) throws ProductNotFoundException;
	List<Product>  getByLesserPrice(double price) throws ProductNotFoundException;
	Product getById(int productId) throws ProductNotFoundException;
	List<String> getProductNames() throws ProductNotFoundException;
	List<Product> getByNameandExpiry(String productName,String date )throws ProductNotFoundException;
	List<Product> getByBrandandLesserPrice(String brand,double price)throws ProductNotFoundException;
}
