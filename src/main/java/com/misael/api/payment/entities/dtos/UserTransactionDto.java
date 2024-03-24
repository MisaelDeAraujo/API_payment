package com.misael.api.payment.entities.dtos;

public record UserTransactionDto(

		Double value,
		
		Integer payer,
		
		Integer payee) {

}
