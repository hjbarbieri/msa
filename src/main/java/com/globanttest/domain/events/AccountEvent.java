package com.globanttest.domain.events;

import java.math.BigDecimal;

public class AccountEvent {

	private BigDecimal amount;
	private Long transactionId;
	private AccountEventType accountType;

	public AccountEvent(BigDecimal amount, Long transactionId,
			AccountEventType accountType) {
		this.transactionId = transactionId;
		this.amount = amount;
		this.accountType = accountType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public AccountEventType getAccountType() {
		return accountType;
	}

}
