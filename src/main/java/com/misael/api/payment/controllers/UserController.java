package com.misael.api.payment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misael.api.payment.entities.dtos.CommonUserDto;
import com.misael.api.payment.services.UserService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	ResponseEntity<Object> registerNewCommonUser(@Valid CommonUserDto dto){
		ResponseEntity.ok().body(userService.save(dto));
	}	
}
