package com.globanttest.interfaces.rest;

import java.math.BigDecimal;
// Represent a write request
public final class OpenAccountCommand implements AccountCommand {

	private final BigDecimal initialBalance;

	public OpenAccountCommand(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}
}