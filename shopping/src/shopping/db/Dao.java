package shopping.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {
	protected static String dbClassName = "com.mysql.jdbc.Driver";
	protected static String dbUrl = "jdbc:mysql://localhost:3306/shopping" ;
	protected static String dbUser = "root";
	protected static String dbPwd = "123123";
	  
	private static Connection conn = null;
	public Dao() {
		  // 获得数据库连接
	      try {
	          if (conn == null) {
	              Class.forName(dbClassName).newInstance();// 注册驱动程序
	              conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);// 连接数据库，创建连接对象
	          } else
	              return;
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }
	  static ResultSet executeQuery(String sql) {// 执行查询操作
	      try {
	          if (conn == null)
	              new Dao();
	          return conn.createStatement().executeQuery(sql);
	      } catch (SQLException e) {
	          e.printStackTrace();
	          return null;
	      }
	  }    
	  static int executeUpdate(String sql) {// 执行其他操作
	      try {
	          if (conn == null)
	              new Dao();
	          return conn.createStatement().executeUpdate(sql);
	      } catch (SQLException e) {
	          e.printStackTrace();
	          return -1;
	      }
	  }
	  public static void close() {// 关闭连接
	      try {
	          conn.close();
	      } catch (SQLException e) {
	          //e.printStackTrace();
	       e.getMessage();
	      } finally {
	          conn = null;
	      }
	  } 

}
