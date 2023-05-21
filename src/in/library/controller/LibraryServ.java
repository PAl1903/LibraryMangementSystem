package in.library.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.library.persistence.Service;
import in.library.persistence.ServiceImpl;
import in.library.dto.*;
/**
 * Servlet implementation class MainServ
 */
@WebServlet("/library/*")
public class LibraryServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Service login;
	private PrintWriter out;

    
    public LibraryServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getRequestURI().endsWith("/searchresult")) {
			
			HttpSession session1=request.getSession(false);
			if(session1==null||session1.getAttribute("role")==null){
			Cookie ck=new Cookie("msg","LoginFirst"); 
			response.addCookie(ck);
			response.sendRedirect("/BookManagementSystem/validate/login");
			return ;
			}else {
				RequestDispatcher rd= request.getRequestDispatcher("/searchresult.jsp");
				rd.forward(request, response);
				
			}
			
			
		}
		if(request.getRequestURI().endsWith("/addbook")) {
			Service service=new ServiceImpl();
			book b=new book();
			b.setISBN(Integer.parseInt(request.getParameter("isbn")));
			b.setAuthorname(request.getParameter("authorname"));
			b.setName(request.getParameter("name"));
			int result=service.addbook(b);
			if(result==0) {
				PrintWriter out = response.getWriter();
				out.println("<h1>Failed To Add Book</h1>");
				return;
			}else {
				RequestDispatcher rd=request.getRequestDispatcher("/sucessadd.html");
				rd.forward(request, response);
				return;
			}
			
			
		}
		if(request.getRequestURI().endsWith("/deletebook")) {
			Service service=new ServiceImpl();
			
			
			int result=service.deletebook(Integer.parseInt(request.getParameter("isbn")));
			if(result==0) {
				PrintWriter out = response.getWriter();
				out.println("<h1>Failed To Add Book</h1>");
				return;
			}else {
				RequestDispatcher rd=request.getRequestDispatcher("/sucessdel.html");
				rd.forward(request, response);
				return;
			}
			
			
		}
		
		if(request.getRequestURI().endsWith("/sucessissue")) {
		
				Service service=new ServiceImpl();
				System.out.println("LibraryServ.doGet()");
				int result=service.issuebook(Integer.parseInt(request.getParameter("isbn")),Integer.parseInt(request.getParameter("id")));
				if(result==0) {
					RequestDispatcher rd=request.getRequestDispatcher("/issuealready.html");
					rd.forward(request, response);
					return;
				}else {
					RequestDispatcher rd=request.getRequestDispatcher("/issuesuccess.html");
					rd.forward(request, response);
					return;
				}
				
			
		}
		if(request.getRequestURI().endsWith("/sucessreturn")) {
			response.setContentType("text/html");
			Service service=new ServiceImpl();
			System.out.println("LibraryServ.doGet()");
			long result=service.returnbook(Integer.parseInt(request.getParameter("isbn")),Integer.parseInt(request.getParameter("id")));
			if(result==-1) {
				PrintWriter out = response.getWriter();
				out.println("<h1>Failed To Return</h1>");
				return;
			}else {
				request.setAttribute("day", -result);
				request.setAttribute("charge", Math.max(0,10*(-result-30)));
				RequestDispatcher rd=request.getRequestDispatcher("/return.jsp");
				rd.forward(request, response);
				return;
			}
		}
		
		if(request.getRequestURI().endsWith("")) {
			HttpSession session=request.getSession(false);

			if(session==null||session.getAttribute("role")==null)
			{
				Cookie ck=new Cookie("msg","LoginFirst"); 
				response.addCookie(ck);
				response.sendRedirect("/BookManagementSystem/validate/login");
			}
			else {
			System.out.println(session.getAttribute("role"));
			  if( (int)session.getAttribute("role")==0) {
				RequestDispatcher rd= request.getRequestDispatcher("/searchbar.html");
				rd.forward(request, response);
			   }
			  else{
				 System.out.println("homelayout");
				RequestDispatcher rd= request.getRequestDispatcher("/homelayout.html");
				rd.forward(request, response);
			  }
		  }
			
		}
		
		
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
