package com.javatpoint.servlet;
import java.sql.*;
public class LoginDao {
	public static boolean validate(String email,String password){
		boolean status=false;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			
			PreparedStatement ps=con.prepareStatement("select * from user4 where email=? and password=?");
			ps.setString(1,email);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			status=rs.next();

		}catch(Exception e){e.printStackTrace();}
		return status;
	}
}
