package com.springboot.mongodb.app.controller;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.result.DeleteResult;
import com.springboot.mongodb.app.model.Account;
import com.springboot.mongodb.app.repository.AccountMongoRepository;
import com.springboot.mongodb.app.service.IAccountService;

@RestController
@RequestMapping(path="/accounts")
public class AccountController {

	//https://javatechonline.com/how-to-implement-redis-cache-in-spring-boot-application/
	//https://www.tutorialspoint.com/using-redis-cache-with-spring-boot
	@Autowired
	private IAccountService accountService;
	
	@Autowired 
	private AccountMongoRepository accountMongoRepository;
	
	@Autowired 
	private MongoTemplate mongoTemplate;
	
	@PostMapping(value="/account",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Account saveAccountDetails(@RequestBody Account account) {		
		account.setTimestamp(getTimeStamp());
		Account accountresp = accountService.saveAccountDetails(account);		
		return accountresp;
	}
	
	//localhost:8070/accounts/account/13131
	//Get Accounts By Id.
	@GetMapping(value="/account/{accountId}")
	@Cacheable(value="Account",key="#accountId",sync=true)
	public ResponseEntity<Account> getAccountDetailsById(@PathVariable Integer accountId) {
		
		Account acccountResp =  accountMongoRepository.findByAccountId(accountId);
		if(acccountResp==null) {			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(acccountResp,HttpStatus.OK);
		}		
	}

	//localhost:8070/accounts/account/status/O
	@GetMapping(value="/account/status/{accountStatus}")
	public ResponseEntity<List<Account>> getAccountsByStatus(@PathVariable String accountStatus) {		
		Query query =  new Query();
		query.addCriteria(Criteria.where("accountStatus").is(accountStatus));
		List<Account> acccountResp =  mongoTemplate.find(query, Account.class);	
		if(org.springframework.util.CollectionUtils.isEmpty(acccountResp)) {			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(acccountResp,HttpStatus.OK);
		}		
	}
	
	//Get All Accounts Details and sort by id.
	//localhost:8070/accounts/accountDetails
	@GetMapping(value="/accountDetails")
	@Cacheable(value="accounts",sync=true)
	public @ResponseBody List<Account> getAllAccountDetails() {		
		List<Account> accountList =  accountMongoRepository.findAll(Sort.by(Sort.Direction.ASC,"accountId"));		
		return accountList;
	}
	
	//localhost:8070/accounts/account
	@CachePut(value="Account")
	@PutMapping(value="/account",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Account updateAccountStatus(@RequestBody Account account) {					
		account.setTimestamp(getTimeStamp());
		account = accountMongoRepository.save(account);
		return account;
	}
	
	@DeleteMapping(value="/account/{accountId}" )
	@CacheEvict(value="Account",key="#accountId")
	public @ResponseBody ResponseEntity<Account> deleteAccountById(@PathVariable Integer accountId) {		
		Query query  = new Query();
		query.addCriteria(Criteria.where("accountId").is(accountId));		
		DeleteResult deleteResult = mongoTemplate.remove(query,Account.class);
		long count = deleteResult.getDeletedCount();
		if(count==0) {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);					
		}
		else {
			return  new ResponseEntity<Account>(HttpStatus.OK);
		}		
	}
	
	private String getTimeStamp() {		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));
		return timeStamp;
	}
	
	public IAccountService getAccountService() {
		return accountService;
	}
	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}
	public AccountMongoRepository getAccountMongoRepository() {
		return accountMongoRepository;
	}
	
	public void setAccountMongoRepository(AccountMongoRepository accountMongoRepository) {
		this.accountMongoRepository = accountMongoRepository;
	}
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
}
