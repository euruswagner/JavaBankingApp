package com.euruswagner.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.euruswagner.services.AccountService;

@WebServlet("/activateAccountServlet")
public class ActivateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService service = new AccountService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int accountId = Integer.parseInt(request.getParameter("accountId"));
		
		int result = service.activateAccount(accountId);
		
		RequestDispatcher rd = request.getRequestDispatcher("/adminHomeServlet");
		HttpSession session = request.getSession();
		session.setAttribute("successMessage", null);
		session.setAttribute("errorMessage", null);
		
		if (result == 1) {			
			session.setAttribute("successMessage", "Account number " + accountId +" is now activated");
			
			rd.forward(request, response);
		} else	{
			session.setAttribute("errorMessage", "There was a problem activating this account.");
			
			rd.forward(request, response);
		}
	}

}
