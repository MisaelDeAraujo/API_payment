package com.misael.api.payment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misael.api.payment.entities.Transaction;
import com.misael.api.payment.entities.dtos.UserTransactionDto;
import com.misael.api.payment.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping
	public ResponseEntity<Transaction> transaction(@RequestBody UserTransactionDto dto){
		Transaction transaction =  (Transaction) transactionService.carryOutTransaction(dto.value(),dto.payer(), dto.payee());
		return ResponseEntity.status(HttpStatus.OK).body(transaction);
	}
	
	
}
