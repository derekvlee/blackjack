package com.dereklee.blackjack.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.namespace.QName;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/")
public class Bank {

	public Bank() {}
	
	@GET
	@Produces({MediaType.APPLICATION_XML})
	public JAXBElement<BankAccount> getXml() {
		return toXml(createBankAccount());
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/balance/{id: \\d+}")
	//e.g. http://localhost:9876/resourcesBank/balance/511
	public String getBalance(@PathParam("id") int accNum) throws JsonProcessingException {
		return toJson(getBankAccountBalance(accNum));
	}	

	private BankAccount getBankAccountBalance(int accNum) {
		return createBankAccount();
	}

	private String toJson(BankAccount bankAcc) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(bankAcc);
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
