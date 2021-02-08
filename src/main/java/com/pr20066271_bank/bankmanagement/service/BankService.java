package com.pr20066271_bank.bankmanagement.service;

import javax.validation.Valid;

import com.pr20066271_bank.bankmanagement.entity.Account;
import com.pr20066271_bank.bankmanagement.entity.Customer;

public interface BankService {
	
	Account createAccount(@Valid Account account);
	
	int transferFund(int fromAccount,int toAccount,double amount);
	
	Customer updateCustomer(Customer customer);
	
	Customer fetchCustomer(int customerId);
	

}
