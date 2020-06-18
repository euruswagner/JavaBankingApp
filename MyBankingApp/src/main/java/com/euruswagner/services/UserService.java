package com.euruswagner.services;

import java.util.List;

import com.euruswagner.daos.UserDAO;
import com.euruswagner.models.User;
import com.euruswagner.templates.LoginTemplate;

public class UserService {
	private UserDAO dao = new UserDAO();
	
	public int create(User u) {
		return dao.create(u);
	}
	
	public User findById(int userId) {
		return dao.findById(userId);
	}
	
	public List<User> findAll() {
		return dao.findAll();
	}
	
	public User login(LoginTemplate lt) {
		User userFromDB = dao.findByUsername(lt.getUsername());
		
		if(userFromDB == null) {
			return null;
		}
		
		if(userFromDB.getPassword().equals(lt.getPassword())) {
			return userFromDB;
		}
		
		return null;
	}
}
