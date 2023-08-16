package shopping.model;

import java.util.Vector;

public class Product {
	private String id;
	private String name;
	private double price;
	private int num;
	
	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public Product(String id,String name,double price,int num) {
		this.id=id;
		this.name=name;
		this.price=price;
		this.num=num;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	

}
