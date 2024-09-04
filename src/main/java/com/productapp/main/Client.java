package com.productapp.main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.productapp.model.Product;
import com.productapp.service.IProductService;
import com.productapp.service.ProductServiceImpl;
import com.productapp.util.ConnectionUtil;

public class Client {

	public static void main(String[] args) {
		IProductService productservice = new ProductServiceImpl();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Please Select the option:");
			System.out.println("1.Insert new product");
			System.out.println("2.Update existing product");
			System.out.println("3.Delete existing product");
			System.out.println("4.Select to view all the products");
			System.out.println("5.View products by Brand");
			System.out.println("6.View products by Category");
			System.out.println("7.View products by LesserPrice");
			System.out.println("8.View product by ProductId");
			System.out.println("9.List ProductNames");
			System.out.println("10.View products by ProductName and ExpiryDate(yyyy-mm-dd)");
			System.out.println("11.View products by Brand and LesserPrice");
			System.out.println("12.Exit");

			int option = sc.nextInt();
			sc.nextLine();
			try {
				switch (option) {

				case 1:
					System.out.println("1.Please enter the product name:");
					String prdName = sc.next();
					System.out.println("2.Please enter the brand");
					String prdBrand = sc.next();
					System.out.println("3.Please enter the category:");
					String prdCategory = sc.next();
					System.out.println("4.Please enter the price:");
					Double price = sc.nextDouble();
					System.out.println("5.Please enter the expiry date (yyyy-MM-dd)");
					String expdate = sc.next();
					LocalDate expirydate = LocalDate.parse(expdate);
					System.out.println("6.Please enter the Manufacturing date (yyyy-MM-dd)");
					String mfgdate = sc.next();
					LocalDate manufdate = LocalDate.parse(mfgdate);
					System.out.println("7.Please enter the WeightinGms:");
					String weightingms = sc.next();
					Product product = new Product(prdName, prdBrand, prdCategory, price, expirydate, manufdate,
							weightingms);
					productservice.addProduct(product);
					break;

				case 2:
					System.out.println("Enter the product_id:");
					int id = sc.nextInt();
					System.out.println("Enter the price to be updated");
					double updateprice = sc.nextDouble();
					productservice.updateProduct(id, updateprice);
					break;

				case 3:
					System.out.println("Enter the product_id to be deleted:");
					int deleteid = sc.nextInt();
					productservice.deleteProduct(deleteid);
					break;

				case 4:
					List<Product> getAll = productservice.getAll();
					getAll.stream().forEach(System.out::println);
					break;

				case 5:
					System.out.println("Please enter the Brand:");
					String brand = sc.next();
					List<Product> getByBrand = productservice.getByBrand(brand);
					getByBrand.stream().forEach(System.out::println);
					break;

				case 6:
					System.out.println("Please enter the Category:");
					String category = sc.next();
					List<Product> getByCategory = productservice.getByCategory(category);
					getByCategory.stream().forEach(System.out::println);
					break;

				case 7:
					System.out.println("Please enter the price:");
					double lessprice = sc.nextDouble();
					List<Product> getByLesserPrice = productservice.getByLesserPrice(lessprice);
					getByLesserPrice.stream().forEach(System.out::println);
					break;
				
				case 8:
					System.out.println("Please enter the product_id:");
					int prdid = sc.nextInt();
					Product getById = productservice.getById(prdid);
					System.out.println(getById);
					break;
					
				case 9:
					List<String> getProductNames = productservice.getProductNames();
					getProductNames.stream().forEach(System.out::println);
					break;
				
				case 10:
					System.out.println("Please enter the product_name:");
					String name = sc.next();
					System.out.println("Please enter the ExpiryDate(yyyy-mm-dd)");
					String prdexpirydate = sc.next();
					List<Product> getByNameandExpiry = productservice.getByNameandExpiry(name,prdexpirydate);
					getByNameandExpiry.stream().forEach(System.out::println);
					break;	

				case 11:
					System.out.println("Please enter the Brand:");
					String requestedbrand = sc.next();
					System.out.println("Please enter the price");
					double requestedprice= sc.nextDouble();
					List<Product> getByBrandandLesserPrice = productservice.getByBrandandLesserPrice(requestedbrand,requestedprice);
					getByBrandandLesserPrice.stream().forEach(System.out::println);
					break;
					
				case 12:
					System.out.println("Thank you");
					System.exit(0);
					break;

				}
			} catch (Exception e) {
				System.out.println("----------OOPS-----------");
				System.out.println(e.getMessage());
				System.out.println("--------------------------");

			}
		}
	}
}
