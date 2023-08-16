package shopping.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import shopping.model.Product;
import shopping.model.ShoppingCart;
import shopping.model.User;

public class CartDao {
	public static int insertCar(String uname,ShoppingCart c) {
		int count=0;
		try {
			String sql="select Userid from users where UserName='"+uname+"'";
			ResultSet rSet=Dao.executeQuery(sql);
			int uid=0;
			if (rSet.next()) { // 查询到编号
				uid=Integer.parseInt(rSet.getString("Userid"));
			}
			sql="insert into car(uid,pid,name,price,num,sum) values("+uid+",'"+c.getPid()+"','"+c.getName()+"',"+c.getPrice()+","+c.getNum()+","+c.getSum()+")";
			count=Dao.executeUpdate(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public static int modifyCar() {
		int count=0;
		return count;
	}
	
}
