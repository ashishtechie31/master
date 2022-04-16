package com.springboot.mongodb.app.repository;

import com.springboot.mongodb.app.model.Account;

public interface AccountCustomRepository {
	
	Account findByAccountStatus(String accountStatus);
}
