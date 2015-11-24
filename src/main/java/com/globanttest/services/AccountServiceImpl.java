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
		try {
			account.processOpenAccount(accountOpenCommand);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void debitAccount(BigDecimal balance, Long accountId) {
		DebitAccountCommand debitAccountCommand = new DebitAccountCommand(balance, accountId);
		try {
			account.processDebitAccount(debitAccountCommand);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void creditAccount(BigDecimal balance, Long accountId) {
		CreditAccountCommand creditAccountCommand = new CreditAccountCommand(balance, accountId);
		try {
			account.processCreditAccount(creditAccountCommand);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
