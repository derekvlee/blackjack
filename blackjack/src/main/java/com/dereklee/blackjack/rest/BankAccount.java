package com.dereklee.blackjack.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bankaccount")
public class BankAccount {
	
	private int accountNumber;
	
	public BankAccount() {}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
}
