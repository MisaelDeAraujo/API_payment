package com.misael.api.payment.entities.dtos;

import com.misael.api.payment.entities.enums.UserType;

import lombok.Builder;

@Builder
public record UserResponseDto(
		String completeName,
		
		String email,
		
		Double wallet,
		
		UserType type) {

}
