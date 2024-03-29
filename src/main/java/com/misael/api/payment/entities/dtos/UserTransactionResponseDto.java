package com.misael.api.payment.entities.dtos;

import lombok.Builder;

@Builder
public record UserTransactionResponseDto(
		Boolean transactionMade) {

}
