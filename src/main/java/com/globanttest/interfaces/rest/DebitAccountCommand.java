package com.globanttest.interfaces.rest;

import java.math.BigDecimal;

public final class DebitAccountCommand implements AccountCommand {
	private final BigDecimal amount;
	private final Long operationId;

	public DebitAccountCommand(BigDecimal amount, Long operationId) {

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