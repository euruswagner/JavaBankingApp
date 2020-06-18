package com.euruswagner.servlets;

import java.io.IOException;
import java.util.HashMap;
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
import com.euruswagner.services.UserService;

@WebServlet("/adminHomeServlet")
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	private AccountService accService = new AccountService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		String successMessage = (String) session.getAttribute("successMessage");
		String errorMessage = (String) session.getAttribute("errorMessage");
		int currentUserId = currentUser.getUserId();
		List<User> nonAdminUsers;
		List<Account> usersAccounts;
		HashMap<Integer, List<Account>> accountsById = new HashMap<Integer, List<Account>>();
		
		if (successMessage != null) {
			request.setAttribute("successMessage", successMessage);
		}
		
		if (errorMessage != null) {
			request.setAttribute("errorMessage", errorMessage);
		}
		
		nonAdminUsers = userService.findAll(); 
		
		for (User user:nonAdminUsers) {
			int userId = user.getUserId();
			usersAccounts = accService.findAccountsByUserId(userId);
			accountsById.put(userId, usersAccounts);
		}
						
		
		request.setAttribute("nonAdminUsers", nonAdminUsers);
		request.setAttribute("accountsById", accountsById);
		request.setAttribute("currentUserName", currentUser.fullName());		
		request.getRequestDispatcher("/adminHome.jsp").forward(request, response);
	}

}
