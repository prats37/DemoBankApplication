package com.pr20066271_bank.bankmanagement.utility;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Name implements Serializable{
	private String firstName;
	private String midName;
	private String lastName;

}
