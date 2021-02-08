package com.pr20066271_bank.bankmanagement.utility;

import java.io.Serializable;

import javax.persistence.Embeddable;


import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address implements Serializable{
	private String presentAddr;
	private String permanentAddr;
}
