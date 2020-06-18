package com.euruswagner.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.euruswagner.models.User;
import com.euruswagner.util.ConnectionFactory;

public class UserDAO implements DAO<User, Integer> {

	@Override
	public int create(User u) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String columns = "user_id, username, first_name, last_name, email, password, role";
			String sql = "INSERT INTO users (" + columns + ") VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, u.getUserId());
			ps.setString(2, u.getUsername());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setString(5, u.getEmail());
			ps.setString(6, u.getPassword());
			ps.setString(7, "Standard");
			
			return ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public List<User> findAll() {
		List<User> nonAdminUsers = new ArrayList<>();
		
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM users WHERE role != 'Admin'";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String username = rs.getString("username");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String role = rs.getString("role");
				
				User user = new User(userId, username, firstName, lastName, email, password, role);
				
				nonAdminUsers.add(user);
			}
			
			return nonAdminUsers;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	public User findById(Integer id) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM users WHERE user_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int userId = rs.getInt("user_id");
				String username = rs.getString("username");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String role = rs.getString("role");
				
				return new User(userId, username, firstName, lastName, email, password, role);
			}
		}	catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User findByUsername(String username) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM users WHERE username = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int userId = rs.getInt("user_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String role = rs.getString("role");
				
				return new User(userId, username, firstName, lastName, email, password, role);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public User update(User obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
