package com.misael.api.payment.entities.dtos;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MerchantUserDto(
		@NotBlank
		@NotNull
		String completeName,
		@NotBlank
		@NotNull
		String password,
		@Email
		String email,
		@CNPJ(message = "invalid CNPJ")
		String cnpj,
		
		Double wallet) {

}
