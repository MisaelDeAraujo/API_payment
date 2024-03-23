package com.misael.api.payment.entities.dtos;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.Email;

public record ShopkeeperUserDto(
		
		String completeName,
		
		String password,
		@Email
		String email,
		@CNPJ
		String cnpj) {

}
