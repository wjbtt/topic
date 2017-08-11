package test;


public class TestUser {
	public static void main(String[] args) {
		String uname = "zl";
		//String pwd = "123";
		String pwd = "123' or 1 = '1";
		//1234:81dc9bdb52d04dc20036dbd8313ed055
		
		User user = UserDAO.findUserByUnameAndPwd1(uname, pwd);
		//User user = UserDAO.findUserByUnameAndPwd1(uname, pwd);
		if(user == null){
			System.out.println("用户名或密码错误！");
		}else{
			System.out.println("welcome,"+user.getUname());
		}
	}
}
