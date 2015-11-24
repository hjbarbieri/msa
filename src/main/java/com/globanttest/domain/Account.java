package com.globanttest.domain;

import java.math.BigDecimal;
import java.util.Random;

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

	private BigDecimal balance;

	public void processOpenAccount(OpenAccountCommand accountOpenCommand) throws Exception {
		AccountEvent accountOpenEvent = new AccountEvent(accountOpenCommand.getInitialBalance(),getRandomId(),AccountEventType.OPEN);
		
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
	
	private Long getRandomId(){
		long range = 1234567L;
		Random r = new Random(); 
		long x = nextLong(r,range);
		
		return x;
	}

	private long nextLong(Random rng, long n) {
		   // error checking and 2^x checking removed for simplicity.
		   long bits, val;
		   do {
		      bits = (rng.nextLong() << 1) >>> 1;
		      val = bits % n;
		   } while (bits-val+(n-1) < 0L);
		   return val;
		}
	
	
	public BigDecimal getBalance() {
		return balance;
	}
}
