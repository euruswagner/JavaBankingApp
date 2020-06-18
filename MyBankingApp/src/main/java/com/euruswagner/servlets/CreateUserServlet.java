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

@WebServlet("/signUpServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = new UserService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User newUser = new User(username, firstName, lastName, email, password);
		int result = service.create(newUser);

		RequestDispatcher rd = request.getRequestDispatcher("/homeServlet");
		
		if (result == 1) {
			HttpSession session = request.getSession();
			int newUserId = newUser.getUserId();
			User currentUser = service.findById(newUserId);
			session.setAttribute("currentUser", currentUser);
			rd.forward(request, response);
		} else {
			if (username.length() == 0 || firstName.length() == 0 || lastName.length() == 0 ||
					email.length() == 0 || password.length() == 0) {
				request.setAttribute("errorMessage", "All fields must be completed to create a User.");
			} else {
				request.setAttribute("errorMessage", "Your User Name and Email must be unique.");
			}
			request.getRequestDispatcher("/signUp.jsp").forward(request, response);
		}
	}
}
