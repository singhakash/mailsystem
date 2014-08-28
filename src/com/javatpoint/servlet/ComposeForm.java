package com.javatpoint.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ComposeForm extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session=request.getSession(false);
		if(session!=null){
		RequestDispatcher rd=request.getRequestDispatcher("compose.html");
		rd.include(request, response);
		}
		else{
			out.print("Sorry! Please Login First");
			RequestDispatcher rd=request.getRequestDispatcher("home.html");
			rd.include(request, response);
		}
		
		out.close();
	}

}
