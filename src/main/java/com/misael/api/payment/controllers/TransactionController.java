package com.misael.api.payment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misael.api.payment.entities.dtos.UserTransactionRequestDto;
import com.misael.api.payment.services.TransactionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@Operation(summary = "Realiza transação entre os usuários",description = "Insira o VALOR da transação,"
			+ "insira o ID do pagador(a) e depois o ID do beneficiário(a)")
	@PostMapping
	public ResponseEntity<String> transaction(@RequestBody UserTransactionRequestDto dto){
		String tp = transactionService.carryOutTransaction(dto.value(),dto.payer(), dto.payee());
		return ResponseEntity.status(HttpStatus.OK).body(tp);
	}
	
	
}
