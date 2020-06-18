package com.euruswagner.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.euruswagner.models.Account;
import com.euruswagner.models.User;
import com.euruswagner.services.AccountService;

@WebServlet("/homeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService accService = new AccountService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		String successMessage = (String) session.getAttribute("successMessage");
		String errorMessage = (String) session.getAttribute("errorMessage");
		int currentUserId = currentUser.getUserId();
		List<Account> usersAccounts;
		
		if (successMessage != null) {
			request.setAttribute("successMessage", successMessage);
		}
		
		if (errorMessage != null) {
			request.setAttribute("errorMessage", errorMessage);
		}
		System.out.println(currentUserId);
		
		usersAccounts = accService.findAccountsByUserId(currentUserId);
						
		if (usersAccounts.isEmpty()) {	
			usersAccounts = null;
		}
		
		request.setAttribute("usersAccounts", usersAccounts);
		request.setAttribute("currentUserName", currentUser.fullName());		
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

}
