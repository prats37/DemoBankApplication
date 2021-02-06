package com.pr20066271_bank.bankmanagement.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pr20066271_bank.bankmanagement.utility.Address;
import com.pr20066271_bank.bankmanagement.utility.Name;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer")
public class Customer implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Embedded
	private Name fullName;
	@Embedded
	private Address address;
	private String email;
	private long mobileNo;
	private long aadharNo;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="acc_id")
	@JsonBackReference
	private Account account;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Name getFullName() {
		return fullName;
	}

	public void setFullName(Name fullName) {
		this.fullName = fullName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public long getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(long aadharNo) {
		this.aadharNo = aadharNo;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Customer(Name fullName, Address address, String email, long mobileNo, long aadharNo) {
		super();
		this.fullName = fullName;
		this.address = address;
		this.email = email;
		this.mobileNo = mobileNo;
		this.aadharNo = aadharNo;
	}
	
	
	
}
