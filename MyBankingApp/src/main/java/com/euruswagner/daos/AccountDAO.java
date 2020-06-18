package com.euruswagner.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.euruswagner.models.Account;
import com.euruswagner.util.ConnectionFactory;

public class AccountDAO implements DAO<Account, Integer> {

	@Override
	public int create(Account acc) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String columns = "account_id, nickname, balance, account_status, account_type";
			String sql = "INSERT INTO accounts (" + columns + ") VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, acc.getAccountId());
			ps.setString(2, acc.getNickname());
			ps.setInt(3, 0);
			ps.setString(4, "Pending");
			ps.setString(5, acc.getAccountType());
			
			return ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int linkUserToAccount(int userId, int accountId) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "INSERT INTO user_account (user_id, account_id) VALUES (?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, userId);
			ps.setInt(2, accountId);
			
			return ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Account> findAccountsByUserId(Integer user_id) {
		List<Account> accountsForUser = new ArrayList<>();
		
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT DISTINCT * FROM accounts WHERE account_id IN"
					+ "(SELECT account_id FROM user_account WHERE user_id = " + user_id + ")";
			PreparedStatement stmt = conn.prepareStatement(sql);
						
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int accountId = rs.getInt("account_id");
				String nickname = rs.getString("nickname");
				int balance = rs.getInt("balance");
				String accountStatus = rs.getString("account_status");
				String accountType = rs.getString("account_type");
				
				Account acc = new Account(accountId, nickname, balance, accountStatus, accountType);

				accountsForUser.add(acc);
			}
			
			return accountsForUser;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Account findById(Integer id) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM accounts WHERE account_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int accountId = rs.getInt("account_id");
				String nickname = rs.getString("nickname");
				int balance = rs.getInt("balance");
				String accountStatus = rs.getString("account_status");
				String accountType = rs.getString("account_type");
				
				return new Account(accountId, nickname, balance, accountStatus, accountType);
			}
		}	catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Account update(Account obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public int accountDeposit(int accountId, int depositAmount ) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, depositAmount);
			stmt.setInt(2, accountId);
			
			int result = stmt.executeUpdate();
			
			return result;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int accountWithdraw(int accountId, int withdrawAmount ) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, withdrawAmount);
			stmt.setInt(2, accountId);
			
			int result = stmt.executeUpdate();
			
			return result;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int activateAccount(int accountId) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "UPDATE accounts SET account_status = 'Active' where account_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, accountId);
			
			int result = stmt.executeUpdate();
			
			return result;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	

}
