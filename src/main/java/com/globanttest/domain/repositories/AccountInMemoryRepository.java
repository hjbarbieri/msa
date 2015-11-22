package com.globanttest.domain.repositories;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.globanttest.domain.events.AccountEvent;

@Repository
public class AccountInMemoryRepository implements AccountEventRepository {

	private List<AccountEvent> events = new ArrayList<>();
	
	@Override
	public void persist(AccountEvent event) {
		events.add(event);
	}


	public List<AccountEvent> getEvents() {
		return events;
	}
	
	
	
	

}
