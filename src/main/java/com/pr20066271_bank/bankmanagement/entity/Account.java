package com.pr20066271_bank.bankmanagement.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="account")
public class Account implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int acc_id;
	
	private String accountType;
	private double balance;
	
	@OneToMany(mappedBy = "account",cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval = true)
	@JsonManagedReference
	Set<Customer> customers;

	public Account(String accountType, double balance, Set<Customer> customers) {
		super();
		this.accountType = accountType;
		this.balance = balance;
		this.customers = customers;
	}
	
	

}
