package com.misael.api.payment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.misael.api.payment.entities.dtos.CommonUserDto;
import com.misael.api.payment.entities.dtos.ShopkeeperUserDto;
import com.misael.api.payment.services.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/commons",method = RequestMethod.POST)
	public ResponseEntity<Object> registerNewCommonUser(@RequestBody @Valid CommonUserDto commonUserDto){
		return ResponseEntity.ok().body(userService.registerNewCommonUser(commonUserDto));
	}
	
	@RequestMapping(value = "/shopkeepers",method = RequestMethod.POST)
	public ResponseEntity<Object> registerNewShopkeeperUser(@RequestBody @Valid ShopkeeperUserDto shopkeeperUserDto){
		return ResponseEntity.ok().body(userService.registerNewShopkeeperUser(shopkeeperUserDto));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> listAllUsers(){
		return ResponseEntity.ok().body(userService.findAll());
	}
}
