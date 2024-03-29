package com.misael.api.payment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misael.api.payment.entities.dtos.UserTransactionRequestDto;
import com.misael.api.payment.entities.dtos.UserTransactionResponseDto;
import com.misael.api.payment.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping
	public ResponseEntity<UserTransactionResponseDto> transaction(@RequestBody UserTransactionRequestDto dto){
		UserTransactionResponseDto tp = transactionService.carryOutTransaction(dto.value(),dto.payer(), dto.payee());
		return ResponseEntity.status(HttpStatus.OK).body(tp);
	}
	
	
}
