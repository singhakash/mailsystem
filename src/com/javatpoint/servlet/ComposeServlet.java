package com.javatpoint.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ComposeServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String to=request.getParameter("to");
		String subject=request.getParameter("subject");
		String message=request.getParameter("message");
		
		HttpSession session=request.getSession(false);
		String from=(String)session.getAttribute("email");
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			
			PreparedStatement ps=con.prepareStatement("insert into usermail(from1,to1,subject1,message1) values(?,?,?,?)");
			ps.setString(1,from);
			ps.setString(2,to);
			ps.setString(3,subject);
			ps.setString(4,message);
			int status=ps.executeUpdate();
			if(status>0){
				out.print("Message successfully sent!");
				RequestDispatcher rd=request.getRequestDispatcher("inbox");
				rd.include(request, response);
			}
			con.close();		
		}catch(Exception e){System.out.println(e);}
		
		
		out.close();
	}

}
