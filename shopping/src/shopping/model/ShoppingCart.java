package shopping.model;

import java.util.ArrayList;
import java.util.Vector;

public class ShoppingCart {
	private int uid;
	private String pid;
	private String name;
	private double price;
	private int num;
	private double sum;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
	private static double total = 0;// 购物车总价
	ArrayList<Product> prolist = new ArrayList<Product>();

	public ArrayList<Product> getProlist() {
		return prolist;
	}

	public void setProlist(ArrayList<Product> prolist) {
		this.prolist = prolist;
	}

	public static double getTotal() {
		return total;
	}

	public static void setTotal(double total) {
		ShoppingCart.total = total;
	}
	// 从商品列表添加商品到购物车
	public int addProduct(Product pro) {
		int i = 0;
		for (Product e : prolist) { // for-each 遍历整个数组
			if (e.getId().equals(pro.getId())) { // 判断获取的商品id是否存在于购物车列表
				int n = e.getNum() + pro.getNum(); // 存在,前者数量加后者数量
				e.setNum(n);
				return i; // 存在，返回非-1的数值
			}
			i++;
		}
		prolist.add(pro); // 将商品信息添加至购物车列表
		total = pro.getPrice() *pro.getNum() + total; // 计算购物车总金额
		return -1; // 不存在，返回-1
	}
}
