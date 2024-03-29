package com.misael.api.payment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misael.api.payment.entities.dtos.UserRequestDto;
import com.misael.api.payment.entities.dtos.UserResponseDto;
import com.misael.api.payment.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserResponseDto> registerNewUser(@RequestBody @Valid UserRequestDto dto){
			UserResponseDto rDto=  userService.saveNewUser(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(rDto);
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponseDto>> listAllUsers(){
		List<UserResponseDto> users = userService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
}
