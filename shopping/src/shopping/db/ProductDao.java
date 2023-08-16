package shopping.db;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import shopping.model.Product;

public class ProductDao {
	public static List<Product> selecProduct(){
		List<Product> list=new ArrayList<Product>();
		String sql="select * from product";
		try {
			ResultSet rSet=Dao.executeQuery(sql);
			
			while(rSet.next()) {
				Product product=new Product(rSet.getString("id"),rSet.getString("name"),rSet.getDouble("price"),rSet.getInt("num"));
					product.setId(rSet.getString("id"));
					product.setName(rSet.getString("name"));
					product.setPrice(rSet.getDouble("price"));
					product.setNum(rSet.getInt("num"));
					list.add(product);
						
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			return list;
	}
	public static List<Product> selectName(String pname){
		List<Product> list=new ArrayList<Product>();
		String sql="select * from product where name like '%"+pname+"%'";
		try {
			ResultSet rSet=Dao.executeQuery(sql);
			while(rSet.next()) {
				Product product=new Product(rSet.getString("id"),rSet.getString("name"),rSet.getDouble("price"),rSet.getInt("num"));
					product.setId(rSet.getString("id"));
					product.setName(rSet.getString("name"));
					product.setPrice(rSet.getDouble("price"));
					product.setNum(rSet.getInt("num"));
					list.add(product);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			return list;
	}
	public static String selectPid(String pid) {
		String proid ="";
		String sql="select id from product where id = '"+pid+"'";
		try {
			ResultSet rSet=Dao.executeQuery(sql);
			while(rSet.next()) {
				proid=rSet.getString("id");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return proid;
	}

}
