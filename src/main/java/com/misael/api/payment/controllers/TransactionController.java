package com.misael.api.payment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.misael.api.payment.entities.dtos.UserTransactionDto;
import com.misael.api.payment.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

	@Autowired
	private UserService service;

	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> transaction(@RequestBody UserTransactionDto dto){
		service.carryOutTransaction(dto);
		return ResponseEntity.ok().build();
	}
	
	
}
