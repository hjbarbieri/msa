package com.globanttest.domain;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.globanttest.domain.events.AccountEvent;
import com.globanttest.domain.events.AccountEventType;
import com.globanttest.domain.repositories.AccountEventRepository;
import com.globanttest.interfaces.rest.CreditAccountCommand;
import com.globanttest.interfaces.rest.DebitAccountCommand;
import com.globanttest.interfaces.rest.OpenAccountCommand;

@Component
public class Account {
	
	@Autowired
	private AccountEventRepository accountEventRepository;

	@Autowired
	private GeneratorId generatorId;
	
	private BigDecimal balance;

	public void processOpenAccount(OpenAccountCommand accountOpenCommand) throws Exception {
		AccountEvent accountOpenEvent = new AccountEvent(accountOpenCommand.getInitialBalance(),generatorId.getRandomId(),AccountEventType.OPEN);
		
		accountEventRepository.persist(accountOpenEvent);
		
	}
	
	public void processDebitAccount(DebitAccountCommand accountDebitCommand) throws Exception {
		AccountEvent accountDebitEvent = new AccountEvent(accountDebitCommand.getAmount(),accountDebitCommand.getOperationId(),AccountEventType.DEBIT);
		accountEventRepository.persist(accountDebitEvent);
		
	}
	
	public void processCreditAccount(CreditAccountCommand accountCreditCommand) throws Exception {
		AccountEvent accountCreditEvent = new AccountEvent(accountCreditCommand.getAmount(),accountCreditCommand.getOperationId(),AccountEventType.CREDIT);
		accountEventRepository.persist(accountCreditEvent);
		
	}
	
	

	public BigDecimal getBalance() {
		return balance;
	}
}
