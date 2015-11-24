package com.globanttest.services;

import java.math.BigDecimal;

public interface AccountService {
	
	public void openAccount(BigDecimal balance);

	public void debitAccount(BigDecimal balance, Long accountId);

	void creditAccount(BigDecimal balance, Long accountId);

}
