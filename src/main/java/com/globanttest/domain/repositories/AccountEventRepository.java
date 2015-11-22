package com.globanttest.domain.repositories;

import com.globanttest.domain.events.AccountEvent;

public interface AccountEventRepository {

	void persist(AccountEvent event);
	
	
}
