package com.BankingApplication.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.service.AccountService;


@RestController
@RequestMapping("api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	//Create or Add new account Rest API
	//{
	//	  "accountHolderName":"sunil sharma ",
	//	  "balance":30000
	 // }
	//http://localhost:8080/api/accounts
	@PostMapping
	 public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		 System.out.println("accountDto :- " + accountDto);
		 return new ResponseEntity<>(accountService.CreateAccount(accountDto),HttpStatus.CREATED); 
	 } 
	
	
	
	//To Get account Details Rest API
	//http://localhost:8080/api/accounts/3
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		
	AccountDto accountDto = accountService.getAccountById(id);
	
	return ResponseEntity.ok(accountDto);
	
	}
	
	
	
	
	//Deposit Amount in account Rest API
	//http://localhost:8080/api/accounts/3/deposit
	// "amount":500 - pass in body
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
		
		Double amount = request.get("amount");
		
		AccountDto accountDto = accountService.deposit(id, amount);
		
	return ResponseEntity.ok(accountDto);	
	}
	
	
	
	//withdraw Amount in account Rest API
	//{ "amount":500} - pass in body
	//http://localhost:8080/api/accounts/3/withdraw
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){
		
	Double amount =	request.get("amount");
	
	AccountDto accountDto = accountService.withdraw(id, amount);
	
	return ResponseEntity.ok(accountDto);
	}
	
	
	
	//To Get all account Details Rest API
	//http://localhost:8080/api/accounts
	@GetMapping
	public ResponseEntity<List <AccountDto>> getAllAccounts(){
		
	List<AccountDto> accountDto = accountService.getAllAccounts();
		
	return ResponseEntity.ok(accountDto);
	}
	
	
	//Delete account Rest API
	//http://localhost:8080/api/accounts/3
	@DeleteMapping("/{id}")
	public ResponseEntity<String>  deleteAccount(@PathVariable Long id){
		
	accountService.deleteAccount(id);
		
	return ResponseEntity.ok("Account Deleted Successfully...!!!");
	}  
	
}
