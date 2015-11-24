package com.globanttest.requests;

import java.math.BigDecimal;

import com.globanttest.domain.events.AccountEventType;

public class AccountRequest {
	
	private BigDecimal balance;
	private Long accountId;
	private AccountEventType accountEventType;
	
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public AccountEventType getAccountEventType() {
		return accountEventType;
	}
	public void setAccountEventType(AccountEventType accountEventType) {
		this.accountEventType = accountEventType;
	}
}
