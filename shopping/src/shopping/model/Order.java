package shopping.model;

import java.util.ArrayList;


public class Order{
	ArrayList<Product> list=new ArrayList<Product>();
	
	double total;
	public Order(ArrayList<Product> orderlist, double total) {
		this.list=orderlist;
		this.total=total;
	}

	public ArrayList<Product> getList() {
		return list;
	}



	public void setList(ArrayList<Product> list) {
		this.list = list;
	}

	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}

