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
	public ResponseEntity<String> openAccount(@RequestBody AccountRequest accountOpenRequest){
		if((accountOpenRequest.getBalance().compareTo(BigDecimal.ZERO) < 0))
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		
		accountService.openAccount(accountOpenRequest.getBalance());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/accounts/{accountId}",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> debitCreditAccount(@RequestBody AccountRequest accountRequest,@PathVariable Long accountId){
		if((accountRequest.getBalance().compareTo(BigDecimal.ZERO) < 0)){
			accountRequest.setAccountEventType(AccountEventType.DEBIT);
			accountService.debitAccount(accountRequest.getBalance(),accountId);
		}else if((accountRequest.getBalance().compareTo(BigDecimal.ZERO) == 0)){
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}else{
			accountRequest.setAccountEventType(AccountEventType.CREDIT);
			accountService.creditAccount(accountRequest.getBalance(),accountId);
		}
			
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
