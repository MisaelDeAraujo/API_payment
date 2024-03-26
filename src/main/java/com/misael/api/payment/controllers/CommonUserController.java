package com.misael.api.payment.controllers;

import com.misael.api.payment.entities.CommonUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.misael.api.payment.entities.dtos.CommonUserDto;
import com.misael.api.payment.services.CommonUserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/commons")
@AllArgsConstructor
public class CommonUserController {

	@Autowired
	private CommonUserService commonUserService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CommonUser> registerNewCommonUser(@RequestBody @Valid CommonUserDto commonUserDto){
			CommonUser user = commonUserService.saveNewCommonUser(commonUserDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CommonUser>> listAllUsers(){
		List<CommonUser> commonUsers = commonUserService.findAll();
		return ResponseEntity.ok().body(commonUsers);
	}
}
