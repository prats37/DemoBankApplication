package com.pr20066271_bank.bankmanagement.contoller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pr20066271_bank.bankmanagement.entity.Account;
import com.pr20066271_bank.bankmanagement.entity.Customer;
import com.pr20066271_bank.bankmanagement.utility.Address;
import com.pr20066271_bank.bankmanagement.utility.Name;


@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int randomPort;
	
	private static Account a1;
	private static Account a2;
	private static Customer c1;
	private static Customer c2;
	private static Address add1;
	private static Address add2;
	private static Name n1;
	private static Name n2;
	private static Set<Customer> customers1=new HashSet<Customer>();
	private static Set<Customer> customers2=new HashSet<Customer>();
	
	private String URI;
	
	@BeforeAll
	public static void init() {
		n1=new Name("Pratyush",null,"Raj");
		n2=new Name("Abhishek",null,"Raj");
		add1=new Address("Hyderbad","Bangalore");
		add2=new Address("Delhi","Mumbai");
		c1=new Customer(n1,add1,"pratyushraj@gmail.com",1234567890,12345678901234l);
		c2=new Customer(n2,add2,"pratyushraj1@gmail.com",1234567891,12345678901235l);
		customers1.add(c1);
		customers2.add(c2);
		a1=new Account("Savings",1000,customers1);
		a2=new Account("Savings",2000,customers2);
	}
	
	@Test
	void testGetDefaultResponse() {
		URI="http://localhost:"+randomPort+"/bank-management/";
		String output=this.restTemplate.getForObject(URI+"/get", String.class).toString();
		System.out.println(randomPort);
		System.out.println(output);
		assertEquals(output, "***Welcome to Bank Demo application***");
	}

	@Test
	void testAddNewCustomer() {
		URI="http://localhost:"+randomPort+"/bank-management/createaccount";
		HttpEntity<Account> request=new HttpEntity<>(a1);
		ResponseEntity<Account> response=restTemplate.postForEntity(URI,request,Account.class);
		assertSame(response.getStatusCode(),HttpStatus.CREATED);
		
	}

	@Test
	void testTransferFund() {
		URI="http://localhost:"+randomPort+"/bank-management/transferfund/2/1/500";
		ResponseEntity<?> response=restTemplate.getForEntity(URI, Integer.class);
		assertSame(response.getStatusCode(),HttpStatus.OK);
	}

	@Test
	void testUpdateCustomer() {
//		URI="http://localhost:"+randomPort+"/bank-management//updatecustomer";
//		c2.setId(1);
//		c2.setEmail("pratyush@gmail.com");
//		HttpEntity<Customer> request=new HttpEntity<>(c2);
//		ResponseEntity<Customer> response=restTemplate.postForEntity(URI,request,Customer.class);
//		//assertEquals(response.getBody(),c1);
//		assertSame(response.getStatusCode(),HttpStatus.OK);
	}

	@Test
	void testFetchCustomer() {
//		URI="http://localhost:"+randomPort+"/bank-management";
//		//HttpEntity<Account> request=new HttpEntity<>(a1);
//		ResponseEntity<?> response=restTemplate.getForEntity(URI+"/fetchCustomer/1", Customer.class);
//		assertSame(response.getStatusCode(),HttpStatus.OK);
	}

}
