package com.globanttest.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globanttest.domain.Account;
import com.globanttest.interfaces.rest.CreditAccountCommand;
import com.globanttest.interfaces.rest.DebitAccountCommand;
import com.globanttest.interfaces.rest.OpenAccountCommand;

@Service
//Adapter converts external request and events into commands
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	//Aggregate Route
	private Account account;
	
	@Override
	public void openAccount(BigDecimal balance) {
		OpenAccountCommand accountOpenCommand = new OpenAccountCommand(balance);
		account.processOpenAccount(accountOpenCommand);
	}

	@Override
	public void debitAccount(BigDecimal balance, Long transactionID) {
		DebitAccountCommand debitAccountCommand = new DebitAccountCommand(balance, transactionID);
		account.processDebitAccount(debitAccountCommand);
		
	}
	
	@Override
	public void creditAccount(BigDecimal balance, Long transactionID) {
		CreditAccountCommand creditAccountCommand = new CreditAccountCommand(balance, transactionID);
		account.processCreditAccount(creditAccountCommand);
		
	}
	
}
