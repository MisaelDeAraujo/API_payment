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

import com.misael.api.payment.entities.CommonUser;
import com.misael.api.payment.entities.dtos.CommonUserDto;
import com.misael.api.payment.services.CommonUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/commons")
public class CommonUserController {

	@Autowired
	private CommonUserService commonUserService;
	
	@PostMapping
	public ResponseEntity<CommonUser> registerNewCommonUser(@RequestBody @Valid CommonUserDto commonUserDto){
			CommonUser user = commonUserService.saveNewCommonUser(commonUserDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@GetMapping
	public ResponseEntity<List<CommonUser>> listAllUsers(){
		List<CommonUser> commonUsers = commonUserService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(commonUsers);
	}
}
