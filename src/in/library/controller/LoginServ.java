package in.library.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.library.persistence.Service;
import in.library.persistence.ServiceImpl;

@WebServlet("/validate/*")
public class LoginServ extends HttpServlet {
	Service login;
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		doprocess(request,response);
	}
	
	private void doprocess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getRequestURI());
		PrintWriter out;
		if(request.getRequestURI().endsWith("login")) {
//			System.out.println(request.getParameter("email"));
//			System.out.println(request.getParameter("password"));
			response.setContentType("text/html");  
			RequestDispatcher rd= request.getRequestDispatcher("/loginsignup.jsp");
			rd.forward(request, response);	
			
		}
		
		if(request.getRequestURI().endsWith("/signup")) {
			System.out.println(request.getParameter("email"));
			System.out.println(request.getParameter("password"));
			
			login=new ServiceImpl();
			int res = login.signup( request.getParameter("email"), request.getParameter("password"));
			if(res==1 && (request.getParameter("email")!=null && request.getParameter("password")!=null) ) {
				RequestDispatcher rd= request.getRequestDispatcher("/SignUpSucess.html");
				rd.forward(request, response);	
			}
			else {
				RequestDispatcher rd= request.getRequestDispatcher("/SignUpfail.html");
				rd.forward(request, response);	
			}
			
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doprocess(request,response);
	}
	

}
