package com.euruswagner.services;

import java.util.List;

import com.euruswagner.daos.AccountDAO;
import com.euruswagner.models.Account;

public class AccountService {
	private AccountDAO dao = new AccountDAO();
	
	public int create(Account acc) {
		return dao.create(acc);
	}
		
	public Account findById(int accountId) {
		return dao.findById(accountId);
	}
	
	public List<Account> findAccountsByUserId(int user_id) {
		return dao.findAccountsByUserId(user_id);
	}
	
	public int linkUserToAccount(int userId, int accountId) {
		return dao.linkUserToAccount(userId, accountId);
	}
	
	public int accountDeposit(int accountId, int depositAmount) {
		return dao.accountDeposit(accountId, depositAmount);
	}
	
	public int accountWithdraw(int accountId, int withdrawAmount) {
		return dao.accountWithdraw(accountId, withdrawAmount);
	}
	
	public int activateAccount(int accountId) {
		return dao.activateAccount(accountId);
	}
}
