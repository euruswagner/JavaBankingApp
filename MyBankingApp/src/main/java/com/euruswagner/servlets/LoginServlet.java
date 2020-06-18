package com.euruswagner.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.euruswagner.models.User;
import com.euruswagner.services.UserService;
import com.euruswagner.templates.LoginTemplate;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = new UserService();
	
	@Override 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		LoginTemplate lt = new LoginTemplate(username, password);
		
		User user = service.login(lt);
		
		
		
		if (user == null) {
			request.setAttribute("errorMessage", "Username or Password are incorrect!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} else if (user.getRole().equals("Admin")) {
			RequestDispatcher rd = request.getRequestDispatcher("/adminHomeServlet");
			HttpSession session = request.getSession();
			session.setAttribute("successMessage", null);
			session.setAttribute("errorMessage", null);

			session.setAttribute("currentUser", user);
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/homeServlet");
			HttpSession session = request.getSession();
			session.setAttribute("successMessage", null);
			session.setAttribute("errorMessage", null);

			session.setAttribute("currentUser", user);
			rd.forward(request, response);
		}
	}
}
