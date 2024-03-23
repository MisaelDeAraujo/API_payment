package com.misael.api.payment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misael.api.payment.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

	@Autowired
	private UserService service;
	
	
	public ResponseEntity<Object> transaction(){
		return null;
	}
	
	
}
