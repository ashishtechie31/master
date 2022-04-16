package com.springboot.mongodb.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mongodb.app.model.Account;
import com.springboot.mongodb.app.repository.IAccountRepository;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountRepository accountRepository;
	
	@Override
	public Account saveAccountDetails(Account account) {
		 Account accountResp = accountRepository.save(account);
		 return accountResp;
	}
	

	public IAccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(IAccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}	
}
