package com.globanttest.interfaces.rest;

import java.math.BigDecimal;

public final class CreditAccountCommand implements AccountCommand {
	private final BigDecimal amount;
	private final Long operationId;

	public CreditAccountCommand(BigDecimal amount, Long operationId) {

		this.amount = amount;
		this.operationId = operationId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Long getOperationId() {
		return operationId;
	}
}