package com.globanttest.requests;

import java.math.BigDecimal;

import com.globanttest.domain.events.AccountEventType;

public class AccountRequest {
	
	private BigDecimal balance;
	private Long transactionID;
	private AccountEventType accountEventType;
	
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Long getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(Long transactionID) {
		this.transactionID = transactionID;
	}
	public AccountEventType getAccountEventType() {
		return accountEventType;
	}
	public void setAccountEventType(AccountEventType accountEventType) {
		this.accountEventType = accountEventType;
	}
}
