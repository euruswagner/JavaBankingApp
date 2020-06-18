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

@WebServlet("/accountDepositServlet")
public class AccountDepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService service = new AccountService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int accountId = Integer.parseInt(request.getParameter("accountId"));
		int depositAmount = Integer.parseInt(request.getParameter("depositAmount"));
		Account account = service.findById(accountId);
		String accountStatus = account.getAccountStatus();
		int currentBalance = account.getBalance();
		int totalBalance = currentBalance + depositAmount;
		
		RequestDispatcher rd = request.getRequestDispatcher("/homeServlet");
		HttpSession session = request.getSession();
		session.setAttribute("successMessage", null);
		session.setAttribute("errorMessage", null);
		
		if (accountStatus.equals("Active")) {
			int result = service.accountDeposit(accountId, totalBalance);
			
			if (result == 1) {			
				session.setAttribute("successMessage", "Yay you now have more money");
			
				rd.forward(request, response);
			} else	{
				session.setAttribute("errorMessage", "There was a problem making your deposit.");
			
				rd.forward(request, response);
			}
		} else {
			session.setAttribute("errorMessage", "This account is not active. Please wait for an Admin to activate this account.");
			
			rd.forward(request, response);
		}
	}
	
}
