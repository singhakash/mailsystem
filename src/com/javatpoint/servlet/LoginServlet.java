package com.javatpoint.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		HttpSession session=request.getSession();
		
		if(LoginDao.validate(email, password)){
			out.print("you are successfully logged in!");
			session.setAttribute("email",email);
			
			RequestDispatcher rd=request.getRequestDispatcher("inbox");
			rd.forward(request, response);
			
		}else{
			out.print("Sorry, username or password error!");
			RequestDispatcher rd=request.getRequestDispatcher("home.html");
			rd.include(request, response);
		}
		
		out.close();
	}

}
