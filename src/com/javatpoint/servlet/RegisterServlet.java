package com.javatpoint.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name=request.getParameter("username");
		String password=request.getParameter("userpass");
		String email=request.getParameter("useremail");
		String country=request.getParameter("usercountry");
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			
			PreparedStatement ps=con.prepareStatement("insert into user4(name,password,email,country) values(?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,password);
			ps.setString(3,email);
			ps.setString(4,country);
			int status=ps.executeUpdate();
			if(status>0)
				out.print("You are successfully registered...");

			con.close();		
		}catch(Exception e){System.out.println(e);}
		
		out.close();
	}

}
