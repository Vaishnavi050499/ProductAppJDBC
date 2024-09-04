package com.productapp.util;

public class Queries {
	public static final String CREATEQUERY = """
			create table IF NOT EXISTS product(product_name varchar(40),
			         product_id int primary key auto_increment,
			         brand varchar(20),
			         category varchar(20),
			         price float,
			         expiry_date date,
			         mfg_date date,
			         weight_in_gms varchar(20))
			""";

	public static final String INSERTQUERY = """
			INSERT INTO PRODUCT(product_name,brand,category,price,mfg_date,
			expiry_date,weight_in_gms) VALUES(?,?,?,?,?,?,?)
			""";

	public static final String UPDATEQUERY = """
			UPDATE PRODUCT SET PRICE=? WHERE PRODUCT_ID=?
			""";

	public static final String DELETEQUERY = """
			Delete from product where product_id=?
			""";

	public static final String VIEWQUERY = """
			Select * from product;
			""";

	public static final String GETBYBRAND = """
			Select * from product where brand=?
			""";

	public static final String GETBYCATEGORY = """
			Select * from product where category=?
			""";

	public static final String GETBYLESSERPRICE = """
			Select * from product where price <= ?;
			""";

	public static final String GETBYID = """
			Select * from product where product_id= ?;

			""";

	public static final String GETBYPRODUCTNAME = """
			Select product_name from product;
			""";

	public static final String GETBYBRANDANDLESSERPRICE = """
			Select * from product where brand = ? and price <= ?;
			""";

	public static final String GETBYNAMEANDEXPIRY = """
			Select * from product where product_name=? and expiry_date =?;
			
			""";

}
