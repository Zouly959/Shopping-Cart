package shopping.db;

import shopping.model.User;

public class UserDao {
	public static int insertUser(User u) {
		int count=0;
		try {
			
			String sql="insert into users(UserName,UserPassword) values('"+u.getUserName()+"','"+u.getPassword()+"')";
			count=Dao.executeUpdate(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}

}
