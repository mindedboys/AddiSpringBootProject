package com.BankingApplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.BankingApplication.dto.AccountDto;

@Service
public interface AccountService {

	  AccountDto CreateAccount(AccountDto account);
	  
	  AccountDto getAccountById(Long id);	 //help of id will get account
	  
	  AccountDto deposit(Long id, double amount);//help of id will get account and add amount
	  
	  AccountDto withdraw(Long id, double amount);//help of id will get account and withdraw amount
	  	  
	  List<AccountDto> getAllAccounts(); // to get all accounts
	  
	  void deleteAccount(Long id);

}
