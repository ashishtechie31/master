package com.springboot.mongodb.app.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Ashish
 *
 */
@Document(collection="account")
public class Account implements Serializable {

	private static final long serialVersionUID = -4520248864273817589L;
	
	
	private Integer accountId;	
	private String accountName;
	private String accountStatus;
	private Integer amount;
	private String currrency;
	private String timestamp;
	
	public Account() {
	
	}
	
	Account(Integer accountId,String accountName,String accountStatus,Integer amount,String currrency) {
		this.accountId=accountId;
		this.accountName=accountName;
		this.accountStatus=accountStatus;
		this.amount=amount;
		this.currrency=currrency;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getCurrrency() {
		return currrency;
	}
	public void setCurrrency(String currrency) {
		this.currrency = currrency;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
