package com.misael.api.payment.entities.dtos;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;

public record CommonUserDto(
		
		String completeName,
		
		String password,
		@Email
		String email,
		@CPF
		String cpf) {

}
