package shopping.model;

import java.util.ArrayList;
import java.util.Vector;

public class DateTransform {
	Product pro;

	public Product getPro() {
		return pro;
	}

	public void setPro(Product pro) {
		this.pro = pro;
	}
	
	public Product productTransform(Vector<Object> v) {//将单行表格信息转化为一个商品信息
		String id = (String) v.get(0);
		String name = (String) v.get(1);
		double price = Double.parseDouble(v.get(2).toString());
		int num = Integer.parseInt(v.get(3).toString());
		pro=new Product(id, name, price, num);
		return pro;
	}

	public static void changeList(ArrayList<Product> prolist, Vector<Vector<Object>> date) //将商品信息从ArrayList转化为Vector，用于表格显示
	{
		// TODO Auto-generated method stub
		for(Product e : prolist){
			Vector<Object> row=new Vector<Object>();
			row.add(e.getId());
			row.add(e.getName());
			row.add(e.getPrice());
			date.add(row);
		}
	}
	
}
