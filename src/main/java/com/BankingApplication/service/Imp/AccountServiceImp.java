package com.BankingApplication.service.Imp;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BankingApplication.Repository.AccountRepository;
import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.entity.Account;
import com.BankingApplication.mapper.AccountMapper;
import com.BankingApplication.service.AccountService;


@Service
public class AccountServiceImp implements AccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Override //To Add account Service logic
	public AccountDto CreateAccount(AccountDto accountDto) {

		Account account = AccountMapper.mapToAccount(accountDto); 
		
	    Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override //To Get account Details Service logic
	public AccountDto getAccountById(Long id) {
          
	 Account account  =	accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account dose not exist"));
		
       return AccountMapper.mapToAccountDto(account);
	}

	
	@Override //Service logic for Add or deposit Amount in account 
	public AccountDto deposit(Long id, double amount) {
	
	    Account account  =	accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account dose not exist"));
	
	    double totalBalance  = account.getBalance() + amount;   
	 
	       account.setBalance(totalBalance);
	 
	       Account savedAccount = accountRepository.save(account);
	
	return AccountMapper.mapToAccountDto(savedAccount);
	}

	
	@Override //Service logic for withdraw Amount in account 
	public AccountDto withdraw(Long id, double amount) {
	
	    Account account  =	accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account dose not exist"));
		//less then balance condition
	    if(account.getBalance()<amount){
	    	throw new RuntimeException("Insufficient Balance");
	    }
	    
	    double totalBalance  = account.getBalance() - amount; 
	      account.setBalance(totalBalance);
         Account savedAccount = accountRepository.save(account);	    
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	
	@Override              //To Get all account Details Service logic
	public List<AccountDto> getAllAccounts() {
		
	return accountRepository.findAll().stream().map((account)->AccountMapper.mapToAccountDto(account)).			collect(Collectors.toList());
		
	}
	
	@Override             //To delete account Service logic
	public void deleteAccount(Long id) {
	 
     Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not Exist"));  
      
     accountRepository.delete(account);
        
	}
}
