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

@WebServlet("/accountTransferServlet")
public class AccountTransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountService service = new AccountService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int fromAccountId = Integer.parseInt(request.getParameter("fromAccountId"));
		int toAccountId = Integer.parseInt(request.getParameter("toAccountId"));
		int transferAmount = Integer.parseInt(request.getParameter("transferAmount"));
		Account fromAccount = service.findById(fromAccountId);
		String fromAccountStatus = fromAccount.getAccountStatus();
		Account toAccount = service.findById(toAccountId);
		String toAccountStatus = toAccount.getAccountStatus();
		int fromAccountBalance = fromAccount.getBalance();
		int toAccountBalance = toAccount.getBalance();
		int newFromAccountBalance = fromAccountBalance - transferAmount;
		int newToAccountBalance = toAccountBalance + transferAmount;
		
		RequestDispatcher rd = request.getRequestDispatcher("/homeServlet");
		HttpSession session = request.getSession();
		session.setAttribute("successMessage", null);
		session.setAttribute("errorMessage", null);
		
		if (fromAccountBalance >= transferAmount && fromAccountStatus.equals("Active") && toAccountStatus.equals("Active")) {
			int withdrawResult = service.accountWithdraw(fromAccountId, newFromAccountBalance);
			int depositResult = service.accountDeposit(toAccountId, newToAccountBalance);
			
			if ((withdrawResult + depositResult) == 2) {
				session.setAttribute("successMessage", "Hooray you transferred money.");
				
				rd.forward(request, response);
			} else	{
				session.setAttribute("errorMessage", "There was a problem making your transfer.");
				
				rd.forward(request, response);
			}
		} else if (fromAccountStatus.equals("Pending") || toAccountStatus.equals("Pending")) {
			session.setAttribute("errorMessage", "One or both accounts are not active. Please wait for an Admin to activate both accounts.");
			
			rd.forward(request, response);
		} else {
			session.setAttribute("errorMessage", "You have insufficient funds for this request.");
			
			rd.forward(request, response);
		}
		
	}

}
