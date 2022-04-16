package com.springboot.mongodb.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.mongodb.app.model.Account;

public interface IAccountRepository extends CrudRepository<Account, String> {
	
}
