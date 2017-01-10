package com.dereklee.blackjack.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.namespace.QName;

@Path("/")
public class Bank {

	public Bank() {}
	
	@GET
	@Produces({MediaType.APPLICATION_XML})
	public JAXBElement<BankAccount> getXml() {
		return toXml(createBankAccount());
	}

	@XmlElementDecl(namespace="http://blackjack.bankaccount", name="bankaccount")
	private JAXBElement<BankAccount> toXml(BankAccount bankAccount) {
		return new JAXBElement<BankAccount>(new QName("bankaccount"), BankAccount.class, bankAccount);
	}

	private BankAccount createBankAccount() {
		BankAccount acc = new BankAccount();
		acc.setAccountNumber(101);
		return acc;
	}
	
	// TODO
	// add toJson, toPlain 
	// see Adages.class and continue reading jax-rs book
}
