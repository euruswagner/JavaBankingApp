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
import com.euruswagner.services.AccountService;
import com.euruswagner.services.UserService;

@WebServlet("/addUserToAccountServlet")
public class AddUserToAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService accService = new AccountService();
	private UserService userService = new UserService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int accountId = Integer.parseInt(request.getParameter("accountId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		User addedUser = userService.findById(userId);
		
		int result = accService.linkUserToAccount(userId, accountId);
		
		RequestDispatcher rd = request.getRequestDispatcher("/adminHomeServlet");
		HttpSession session = request.getSession();
		session.setAttribute("successMessage", null);
		session.setAttribute("errorMessage", null);
		
		if (result == 1) {			
			session.setAttribute("successMessage", "User " + addedUser.fullName() +" is now added to account number " + accountId);
			
			rd.forward(request, response);
		} else	{
			session.setAttribute("errorMessage", "There was a problem adding a user to this account.");
			
			rd.forward(request, response);
		}
	}

}
