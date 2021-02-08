package com.pr20066271_bank.bankmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pr20066271_bank.bankmanagement.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer>{

}
