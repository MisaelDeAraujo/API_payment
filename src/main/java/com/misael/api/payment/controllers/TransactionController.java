package com.misael.api.payment.controllers;

import com.misael.api.payment.entities.Transaction;
import com.misael.api.payment.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.misael.api.payment.entities.dtos.UserTransactionDto;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Transaction> transaction(@RequestBody UserTransactionDto dto){
		Transaction transaction =  transactionService.carryOutTransaction(dto.value(),dto.payer(), dto.payee());
		return ResponseEntity.status(HttpStatus.OK).body(transaction);
	}
	
	
}
