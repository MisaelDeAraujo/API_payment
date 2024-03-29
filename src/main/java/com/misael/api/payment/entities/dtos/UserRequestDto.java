package com.misael.api.payment.entities.dtos;

public record UserRequestDto(
		String completeName,
		
		String password,
		
		String email,
		
		String document,
		
		Double wallet) {

}
