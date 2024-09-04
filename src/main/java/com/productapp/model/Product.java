package com.productapp.model;

import java.time.LocalDate;

public class Product {
	private String productName;
	private Integer productId;
	private String brand;
	private double price;
	private String category;
	private LocalDate expiryDate;
	private LocalDate mfgDate;
	private String weightInGms;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String productName, String brand, String category,double price, 
			LocalDate expiryDate, LocalDate mfgDate, String weightInGms) {
		super();
		this.productName = productName;
		this.brand = brand;
		this.category = category;
		this.price = price;
		this.expiryDate = expiryDate;
		this.mfgDate = mfgDate;
		this.weightInGms = weightInGms;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public LocalDate getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(LocalDate mfgDate) {
		this.mfgDate = mfgDate;
	}

	public String getWeightInGms() {
		return weightInGms;
	}

	public void setWeightInGms(String weightInGms) {
		this.weightInGms = weightInGms;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productId=" + productId + ", brand=" + brand + ", price="
				+ price + ", category=" + category + ", expiryDate=" + expiryDate + ", mfgDate=" + mfgDate
				+ ", weightInGms=" + weightInGms + "]";
	}

	
	
}
