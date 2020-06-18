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
import com.euruswagner.services.AccountService;

@WebServlet("/createAccountServlet")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService service = new AccountService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		String accountType = request.getParameter("accountType");
		
		Account newAccount = new Account(nickname, accountType);
		int result = service.create(newAccount);
		
		
		if (result == 1) {
			RequestDispatcher rd = request.getRequestDispatcher("/linkUserToAccountServlet");
			HttpSession session = request.getSession();
			int currentAccountId = newAccount.getAccountId();
			Account currentAccount = service.findById(currentAccountId);
			session.setAttribute("currentAccount", currentAccount);
			
			rd.forward(request, response);
			
			
		} else	{
			request.setAttribute("errorMessage", "There was a problem creating this account.");
			request.getRequestDispatcher("/createAccount.jsp").forward(request, response);
		}
		
		
	}

}
