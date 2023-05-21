package in.library.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.library.persistence.ServiceImpl;

@WebFilter("/library")
public class loginfilter extends HttpFilter implements Filter {

	private ServiceImpl login;
	private PrintWriter out;

	public loginfilter() {
		super();
	}

	public void destroy() {
	}

	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (request.getParameter("email") != null && request.getParameter("password") != null) {
			 login=new ServiceImpl();
				System.out.println(request.getParameter("email"));
				System.out.println(request.getParameter("password"));
				
				
				int[] res = login.login(request.getParameter("email"), request.getParameter("password"));

				if(res[0]!=0) {
					response.setContentType("text/html");
					HttpSession session = request.getSession();
					session.setMaxInactiveInterval(12);
					session.setAttribute("id",res[0]);
					session.setAttribute("role", res[1]);
				    session.setMaxInactiveInterval(10*60);  //10 minutes

					chain.doFilter(request, response);
				}
				else {
					out=response.getWriter();
					Cookie ck=new Cookie("msg","InvalidCredential"); 
					response.addCookie(ck);
					out.print("");
					response.sendRedirect("/BookManagementSystem/validate/login");
//					out.print("Hello");
				}
			
		}
		
		else if (request.getSession(false)!= null && request.getSession(false).getAttribute("id")!=null) {
			chain.doFilter(request, response);
		}

		else {
			
			Cookie ck=new Cookie("msg","LoginFirst"); 
			response.addCookie(ck);
			response.sendRedirect("/BookManagementSystem/validate/login");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
