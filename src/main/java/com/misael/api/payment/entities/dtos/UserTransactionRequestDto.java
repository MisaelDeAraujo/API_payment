package com.misael.api.payment.entities.dtos;

public record UserTransactionRequestDto(

		Double value,
		
		Integer payer,
		
		Integer payee) {

}
