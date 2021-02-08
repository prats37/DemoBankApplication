package com.pr20066271_bank.bankmanagement.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pr20066271_bank.bankmanagement.dao.AccountRepository;
import com.pr20066271_bank.bankmanagement.dao.CustomerRepository;
import com.pr20066271_bank.bankmanagement.entity.Account;
import com.pr20066271_bank.bankmanagement.entity.Customer;

@Service
public class BankServiceImpl implements BankService{
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	AccountRepository accountRepo;

	@Override
	public Account createAccount(Account account) {
		//Set<Customer> customers=new HashSet<>();
		//customers=account.getCustomers();
		//account.setCustomers(customers);
		Account newAccount=accountRepo.save(account);
		return newAccount;
	}

	@Override
	public int transferFund(int fromAccountNo, int toAccountNo, double amount) {
		int status=0;
		Optional<Account> fromAccount=accountRepo.findById(fromAccountNo);
		if(fromAccount==null) {
			return status;
		}else {
			double fromAccBal=fromAccount.get().getBalance();
			if(fromAccBal<amount) {
				return status;
			}else {
				Optional<Account> toAccount=accountRepo.findById(toAccountNo);
				if(toAccount==null) {
					return status;
				}else {
					Account updatedFromAcc=new Account();
					Account updatedToAcc=new Account();
					updatedFromAcc.setAcc_id(fromAccount.get().getAcc_id());
					updatedFromAcc.setAccountType(fromAccount.get().getAccountType());
					updatedFromAcc.setBalance(fromAccount.get().getBalance()-amount);
					updatedFromAcc.setCustomers(fromAccount.get().getCustomers());
					accountRepo.save(updatedFromAcc);
					updatedToAcc.setAcc_id(toAccount.get().getAcc_id());
					updatedToAcc.setAccountType(toAccount.get().getAccountType());
					updatedToAcc.setBalance(toAccount.get().getBalance()+amount);
					updatedToAcc.setCustomers(toAccount.get().getCustomers());
					accountRepo.save(updatedToAcc);
					status=1;
					return status;
				}
			}
		}
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Optional<Customer> foundCustomer=customerRepo.findById(customer.getId());
		if(foundCustomer.get().equals(null)) {
			return null;
		}else {
			Customer updatedCustomer=new Customer();
			Customer afterUpdate=new Customer();
			updatedCustomer.setAddress(customer.getAddress());
			updatedCustomer.setEmail(customer.getEmail());
			updatedCustomer.setMobileNo(customer.getMobileNo());
			updatedCustomer.setAadharNo(foundCustomer.get().getAadharNo());
			updatedCustomer.setId(foundCustomer.get().getId());
			updatedCustomer.setFullName(customer.getFullName());
			afterUpdate=customerRepo.saveAndFlush(updatedCustomer);
			return afterUpdate;
		}
	}

	@Override
	public Customer fetchCustomer(int customerId) {
		Optional<Customer> foundCustomer=customerRepo.findById(customerId);
		if(foundCustomer!=null) {
			Customer foundCustomerDetail=new Customer();
			foundCustomerDetail.setId(foundCustomer.get().getId());
			foundCustomerDetail.setEmail(foundCustomer.get().getEmail());
			foundCustomerDetail.setAddress(foundCustomer.get().getAddress());
			foundCustomerDetail.setMobileNo(foundCustomer.get().getMobileNo());
			foundCustomerDetail.setFullName(foundCustomer.get().getFullName());
			foundCustomerDetail.setAadharNo(foundCustomer.get().getAadharNo());
			foundCustomerDetail.setAccount(foundCustomer.get().getAccount());
			return foundCustomerDetail;
		}else {
			return null;
		}
	}
	
}
