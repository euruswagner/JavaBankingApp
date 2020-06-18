package com.euruswagner.driver;

import com.euruswagner.daos.AccountDAO;
import com.euruswagner.daos.UserDAO;
import com.euruswagner.models.Account;
import com.euruswagner.models.User;

public class Driver {

	public static void main(String[] args) {
//		UserDAO userDao = new UserDAO();
//		
//		User test = new User("TestUserName", "TestFirst", "TestLast", "test@email.com", "1234");
//		System.out.println(userDao.create(test));
//		
//		AccountDAO accDao = new AccountDAO();
//		
//		Account testNoNickname = new Account("Checking");
//		System.out.println(accDao.create(testNoNickname));
		
		AccountDAO accDao = new AccountDAO();
		
		Account testNickname = new Account("Slush Fund", "Checking");
		System.out.println(accDao.create(testNickname));
	}

}
