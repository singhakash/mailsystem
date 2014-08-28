package com.javatpoint.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InboxServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.print("<h1>Welcome to Inbox</h1>");
		out.print("<a href='logout'>Logout</a> | ");
		out.print("<a href='composeform'>Compose Mail</a><br>");
		
		HttpSession session=request.getSession(false);
		String email=(String)session.getAttribute("email");
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			
			PreparedStatement ps=con.prepareStatement("select * from usermail where to1=?");
			ps.setString(1,email);
			ResultSet rs=ps.executeQuery();
			
			out.print("<table border='1'>");
			out.print("<tr><th>From</th><th>Subject</th><th>Message</th></tr>");
			
			while(rs.next()){
				out.print("<tr><td>"+rs.getString("from1")+"</td><td>"+rs.getString("subject1")+"</td><td>"+rs.getString("message1")+"</td></tr>");
			}
			out.print("</table>");
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		out.close();
	}

}
