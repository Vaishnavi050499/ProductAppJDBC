package com.productapp.service;
import java.util.List;
import java.util.stream.Collectors;

import com.productapp.dao.IProductDao;
import com.productapp.dao.ProducDaotImpl;
import com.productapp.exception.ProductNotFoundException;
import com.productapp.model.Product;

public class ProductServiceImpl implements IProductService {
	private IProductDao productDao = new ProducDaotImpl();

	

	@Override
	public void addProduct(Product product) {
		productDao.addProduct(product);
	}

	@Override
	public void updateProduct(int productId, double price) {
		productDao.updateProduct(productId, price);
	}

	@Override
	public void deleteProduct(int productId) {
		productDao.deleteProduct(productId);

	}

	@Override
	public List<Product> getAll() {
		List<Product> products = productDao.getAll();
		return products;
	}

	@Override
	public List<Product> getByBrand(String brand) throws ProductNotFoundException{
		List<Product> getByBrand = productDao.getByBrand(brand);

		if(getByBrand.isEmpty())
			throw new ProductNotFoundException("No items available");
		return getByBrand;
		
	}

	@Override
	public List<Product> getByCategory(String category) throws ProductNotFoundException{
		
		List<Product> getByCategory = productDao.getByCategory(category);

		if(getByCategory.isEmpty())
			throw new ProductNotFoundException("No products found with given category");
		return getByCategory;
	}

	@Override
	public List<Product> getByLesserPrice(double price) throws ProductNotFoundException{
		List<Product> getByLesserPrice = productDao.getByLesserPrice(price);

		if(getByLesserPrice.isEmpty())
			throw new ProductNotFoundException("No products found with given price");
		return getByLesserPrice;
	}

	@Override
	public Product getById(int productId) throws ProductNotFoundException{
		Product product = productDao.getById(productId);
		if(product==null)
			throw new ProductNotFoundException("Id is not available");
		return product;
	}

	@Override
	public List<String> getProductNames() throws ProductNotFoundException{
		return productDao.getProductNames().stream().sorted().collect(Collectors.toList());
		
	}

	@Override
	public List<Product> getByNameandExpiry(String productName, String date) throws ProductNotFoundException{
		List<Product> getByNameandExpiry= productDao.getByNameandExpiry(productName, date);
	    if(getByNameandExpiry.isEmpty())
			throw new ProductNotFoundException("No product available for the given name and expiry date");
return getByNameandExpiry;
	}

	@Override
	public List<Product> getByBrandandLesserPrice(String brand, double price) throws ProductNotFoundException{
		List<Product> getByBrandandLesserPrice= productDao.getByBrandandLesserPrice(brand, price);
	    if(getByBrandandLesserPrice.isEmpty())
			throw new ProductNotFoundException("No product available for the given brand and price");
return getByBrandandLesserPrice;
	}

	
}
