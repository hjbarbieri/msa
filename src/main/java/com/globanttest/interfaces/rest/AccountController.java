package com.globanttest.interfaces.rest;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.globanttest.domain.events.AccountEventType;
import com.globanttest.requests.AccountRequest;
import com.globanttest.services.AccountService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@RequestMapping(value="/accounts",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> openAccountEvent(@RequestBody AccountRequest accountOpenRequest){
		if((accountOpenRequest.getBalance().compareTo(BigDecimal.ZERO) < 0))
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		
		accountService.openAccount(accountOpenRequest.getBalance());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/accounts/{accountId}",method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> debitAccountEvent(@RequestBody AccountRequest accountRequest,@PathVariable Long accountID){
		if(accountRequest.getAccountEventType() == AccountEventType.DEBIT)
			accountService.debitAccount(accountRequest.getBalance(),accountID);
		else if(accountRequest.getAccountEventType() == AccountEventType.CREDIT)
			accountService.creditAccount(accountRequest.getBalance(),accountID);
			
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
