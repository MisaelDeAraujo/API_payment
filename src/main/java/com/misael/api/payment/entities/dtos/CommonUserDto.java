package com.misael.api.payment.entities.dtos;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommonUserDto(
		@NotBlank(message= "complete name field cannot be empty")
		@NotNull(message= "complete name field cannot be null")
		@Length(max = 50, message = "complete name field exceeded character limit")
		String completeName,
		@NotBlank(message= "password name field cannot be empty")
		@NotNull(message= "password name field cannot be null")
		@Length(max = 60, message= "password field exceeded character limit")
		String password,
		@NotBlank(message= "email field cannot be empty")
		@NotNull(message= "email field cannot be null")
		@Email(message = "invalid email")
		@Length(max = 60, message= "email field exceeded character limit")
		String email,
		@NotBlank(message= "cpf field cannot be empty")
		@NotNull(message= "cpf field cannot be null")
		@CPF(message = "invalid cpf")
		@Length(max = 11, min = 11)
		String cpf,
		
		Double wallet) {

}
