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

@WebServlet("/accountWithdrawServlet")
public class AccountWithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountService service = new AccountService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int accountId = Integer.parseInt(request.getParameter("accountId"));
		int withdrawAmount = Integer.parseInt(request.getParameter("withdrawAmount"));
		Account account = service.findById(accountId);
		String accountStatus = account.getAccountStatus();
		int currentBalance = account.getBalance();
		int remainingBalance = currentBalance - withdrawAmount;
		
		RequestDispatcher rd = request.getRequestDispatcher("/homeServlet");
		HttpSession session = request.getSession();
		session.setAttribute("successMessage", null);
		session.setAttribute("errorMessage", null);
		
		if (currentBalance >= withdrawAmount && accountStatus.equals("Active")) {
			int result = service.accountWithdraw(accountId, remainingBalance);
			
			if (result == 1) {			
				session.setAttribute("successMessage", "You are now one step closer to financial ruin.");
				
				rd.forward(request, response);
			} else	{
				session.setAttribute("errorMessage", "There was a problem making your withdrawal.");
				
				rd.forward(request, response);
			}
		} else if (accountStatus.equals("Pending")) {
			session.setAttribute("errorMessage", "This account is not active. Please wait for an Admin to activate this account.");
			
			rd.forward(request, response);
		} else {	
			session.setAttribute("errorMessage", "You have insufficient funds for this request.");
			
			rd.forward(request, response);
		}
		
		
	}

}
