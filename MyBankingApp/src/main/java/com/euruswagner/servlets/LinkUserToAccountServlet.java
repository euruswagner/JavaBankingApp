package com.euruswagner.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.euruswagner.models.Account;
import com.euruswagner.models.User;
import com.euruswagner.services.AccountService;

@WebServlet("/linkUserToAccountServlet")
public class LinkUserToAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AccountService service = new AccountService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		Account currentAccount = (Account) session.getAttribute("currentAccount");
		int userId = currentUser.getUserId();
		int accountId = currentAccount.getAccountId();
		
		int result = service.linkUserToAccount(userId, accountId);
		
		RequestDispatcher rd = request.getRequestDispatcher("/homeServlet");
		if (result == 1) {
			session.setAttribute("successMessage", "Account successfully created!");
			rd.forward(request, response);
		} else {
			session.setAttribute("errorMessage", "We are very sorry but there was an error.");
			rd.forward(request, response);
		}
	}
}
