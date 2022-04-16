package com.springboot.mongodb.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.mongodb.app.model.Account;

@Repository
public interface AccountMongoRepository extends MongoRepository<Account, Long> {
	
	public Account findByAccountId(Integer accountId);
	
	@Query("{accountStatus:'?A'}")
	public List<Account> findActiveAccounts(String accountStatus);
	

}
