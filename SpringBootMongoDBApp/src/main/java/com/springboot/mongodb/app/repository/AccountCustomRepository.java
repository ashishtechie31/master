package com.springboot.mongodb.app.repository;

import org.springframework.cache.annotation.Cacheable;

import com.springboot.mongodb.app.model.Account;

public interface AccountCustomRepository {
	
	@Cacheable
	Account findByAccountStatus(String accountStatus);
}
