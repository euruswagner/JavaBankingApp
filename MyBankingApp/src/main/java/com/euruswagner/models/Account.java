package com.euruswagner.models;

public class Account {
	private int accountId;
	private String nickname;
	private int balance;
	private String accountStatus;
	private String accountType;
	
	private static int incrementingAccountId = 1;
	
	public Account(int accountId, String nickname, int balance, String accountStatus, String accountType) {
		super();
		this.accountId = accountId;
		this.nickname = nickname;
		this.balance = balance;
		this.accountStatus = accountStatus;
		this.accountType = accountType;
	}

	public Account(String nickname, String accountType) {
		super();
		this.nickname = nickname;
		this.accountType = accountType;
		setAccountId();
	}
	
	public Account(String accountType) {
		super();
		this.accountType = accountType;
		setAccountId();
	}
	
	public Account() {
		super();
	}
	
	private void setAccountId() {
		this.accountId = incrementingAccountId;
		incrementingAccountId++;
	}
	public int getAccountId() {
		return accountId;
	}
	
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getBalance() {
		return balance;
	}
			
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", nickname=" + nickname + ", balance=" + balance
				+ ", accountStatus=" + accountStatus + ", accountType=" + accountType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + ((accountStatus == null) ? 0 : accountStatus.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + balance;
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		if (accountStatus == null) {
			if (other.accountStatus != null)
				return false;
		} else if (!accountStatus.equals(other.accountStatus))
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (balance != other.balance)
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		return true;
	}
	
			
}
