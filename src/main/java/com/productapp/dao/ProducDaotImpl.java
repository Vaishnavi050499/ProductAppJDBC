package com.productapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.productapp.exception.ProductNotFoundException;
import com.productapp.model.Product;
import com.productapp.util.ConnectionUtil;
import com.productapp.util.Queries;

public class ProducDaotImpl implements IProductDao {
	static Connection connection = null;
	static {
		connection = ConnectionUtil.openConnection();
	}

	@Override
	public void addProduct(Product product) {
		try (PreparedStatement ps = connection.prepareStatement(Queries.INSERTQUERY);) {
			ps.setString(1, product.getProductName());
			ps.setString(2, product.getBrand());
			ps.setString(3, product.getCategory());
			ps.setDouble(4, product.getPrice());
			ps.setDate(5, Date.valueOf(product.getMfgDate())); // Convert localdate to sql date
			ps.setDate(6, Date.valueOf(product.getExpiryDate()));
			ps.setString(7, product.getWeightInGms());
			boolean inserted = ps.execute();
			System.out.println("Inserted successfully" + " " + !inserted);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateProduct(int productId, double price) {
		try (PreparedStatement ps = connection.prepareStatement(Queries.UPDATEQUERY);) {
			ps.setInt(2, productId);
			ps.setDouble(1, price);
			int updatecount = ps.executeUpdate();
			System.out.println("Updated Successfully" + " " + updatecount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProduct(int productId) {
		try (PreparedStatement ps = connection.prepareStatement(Queries.DELETEQUERY);) {
			ps.setInt(1, productId);
			ps.execute();
			System.out.println("Deleted Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> getAll() {
		List<Product> getAll = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(Queries.VIEWQUERY); 
				ResultSet rs = ps.executeQuery();) {
			getAll = convertToProductList(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAll;
	}

	@Override
	public List<Product> getByBrand(String brand) {
		List<Product> getByBrand = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(Queries.GETBYBRAND)){
			ps.setString(1,brand);
			try (ResultSet rs = ps.executeQuery()){
			getByBrand = convertToProductList(rs);}}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return getByBrand;
	}
		
		

	@Override
	public List<Product> getByCategory(String category) {
		List<Product> getByCategory = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(Queries.GETBYCATEGORY)){
			ps.setString(1,category);
			try (ResultSet rs = ps.executeQuery()){
				getByCategory = convertToProductList(rs);}}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return getByCategory;
	}

	@Override
	public List<Product> getByLesserPrice(double price) {
		List<Product> getByLesserPrice = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(Queries.GETBYLESSERPRICE)){
			ps.setDouble(1,price);
			try (ResultSet rs = ps.executeQuery()){
				getByLesserPrice = convertToProductList(rs);}}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return getByLesserPrice;
	}

	@Override
	public Product getById(int productId) {
		Product product =null;
		try (PreparedStatement ps = connection.prepareStatement(Queries.GETBYID)){
			ps.setInt(1,productId);
			try (ResultSet rs = ps.executeQuery();){
				while(rs.next())
				{
				product = new Product();
				product.setProductName(rs.getString("product_name"));
				product.setProductId(rs.getInt("product_id"));
				product.setBrand(rs.getString("brand"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getDouble("price"));
				product.setExpiryDate(rs.getDate("expiry_date").toLocalDate());
				product.setMfgDate(rs.getDate("mfg_date").toLocalDate());
				product.setWeightInGms(rs.getString("weight_in_gms"));
			}}}
		 catch (Exception e) {
			e.printStackTrace();
		 }
		return product;
	}

	@Override
	public List<String> getProductNames() {
		List<String> getProductNames=new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(Queries.GETBYPRODUCTNAME);
			ResultSet rs = ps.executeQuery();){
			while(rs.next())
			{
				getProductNames.add(rs.getString("product_name"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getProductNames;
	}

	@Override
	public List<Product> getByNameandExpiry(String productName, String date) {
		List<Product> getByNameandExpiry = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(Queries.GETBYNAMEANDEXPIRY)){
			ps.setString(1,productName);
			LocalDate expirydate = LocalDate.parse(date);
			ps.setDate(2,Date.valueOf(expirydate));
		try(ResultSet rs = ps.executeQuery();){
			getByNameandExpiry = convertToProductList(rs);
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
return getByNameandExpiry;
	}

	@Override
	public List<Product> getByBrandandLesserPrice(String brand, double price) {
		List<Product> getByBrandandLesserPrice = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(Queries.GETBYBRANDANDLESSERPRICE)){
			ps.setString(1,brand);
			ps.setDouble(2,price);
			try (ResultSet rs = ps.executeQuery()){
				getByBrandandLesserPrice = convertToProductList(rs);}}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return getByBrandandLesserPrice;
	}
	

	private List<Product> convertToProductList(ResultSet rs) {
		List<Product> products = new ArrayList<>();
		try {
			while (rs.next()) {
				Product product=new Product();
				product.setProductName(rs.getString("product_name"));
				product.setProductId(rs.getInt("product_id"));
				product.setBrand(rs.getString("brand"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getDouble("price"));
				product.setExpiryDate(rs.getDate("expiry_date").toLocalDate());
				product.setMfgDate(rs.getDate("mfg_date").toLocalDate());
				product.setWeightInGms(rs.getString("weight_in_gms"));
				products.add(product);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;

	}

}
