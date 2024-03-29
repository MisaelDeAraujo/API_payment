package com.misael.api.payment.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserType {

	COMMON("COMMON"),
	MERCHANT("MERCHANT");
	
	private final String userType;


}
