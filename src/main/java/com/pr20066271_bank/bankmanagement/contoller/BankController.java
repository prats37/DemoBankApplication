package com.pr20066271_bank.bankmanagement.contoller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pr20066271_bank.bankmanagement.entity.Account;
import com.pr20066271_bank.bankmanagement.entity.Customer;
import com.pr20066271_bank.bankmanagement.service.BankService;
import com.pr20066271_bank.bankmanagement.utility.CustomError;

@RestController
@RequestMapping("/bank-management")
public class BankController {
	@Autowired
	BankService bankService;
	
	@GetMapping("/get")
	public String getDefaultResponse() {
		return "***Welcome to Bank Demo application***";
	}
	
	@PostMapping("/createaccount")
	public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Account account){
		ResponseEntity<?> response=new ResponseEntity(new CustomError("Unable to add account because of unique constraint violation")
				+account.toString(),HttpStatus.CONFLICT);
		if(account!=null) {
			Account createdAccount=bankService.createAccount(account);
			response=new ResponseEntity<>(createdAccount,HttpStatus.CREATED);
		}else {
			response=new ResponseEntity<>("Customer Detail should not be blank",HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@GetMapping("/transferfund/{fromAcc}/{toAcc}/{amount}")
	public ResponseEntity<?> transferFund(@PathVariable("fromAcc") int fromAccountNo,@PathVariable("toAcc") int toAccountNo,@PathVariable("amount") double amount){
		ResponseEntity<?> response=new ResponseEntity(new CustomError("Unable to transfer fund because of unique constraint violation")
				,HttpStatus.CONFLICT);
		if(fromAccountNo>0 && toAccountNo>0 && amount>0) {
			int status=bankService.transferFund(fromAccountNo,toAccountNo,amount);
			response=new ResponseEntity<>(status,HttpStatus.OK);
		}else {
			response=new ResponseEntity<>("Invalid request",HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PostMapping("/updatecustomer")
	public ResponseEntity<?> updateCustomer(@Valid @RequestBody Customer customer){
		ResponseEntity<?> response=new ResponseEntity(new CustomError("Unable to update customer because of unique constraint violation")
				,HttpStatus.CONFLICT);
		if(customer!=null) {
			Customer updatedCustomer=bankService.updateCustomer(customer);
			response=new ResponseEntity<>(updatedCustomer,HttpStatus.OK);
		}else {
			response=new ResponseEntity<>("Invalid request",HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@GetMapping("/fetchCustomer/{id}")
	public ResponseEntity<?> fetchCustomer(@PathVariable int id){
		ResponseEntity<?> response=new ResponseEntity(new CustomError("Unable to fetch customer because of unique constraint violation")
				,HttpStatus.CONFLICT);
		if(id>0) {
			Customer fetchedCustomer=bankService.fetchCustomer(id);
			response=new ResponseEntity<>(fetchedCustomer,HttpStatus.OK);
		}else {
			response=new ResponseEntity<>("Invalid request",HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	

}
