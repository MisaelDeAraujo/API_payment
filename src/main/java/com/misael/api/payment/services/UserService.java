package com.misael.api.payment.services;

import org.springframework.stereotype.Service;

import com.misael.api.payment.entities.User;
import com.misael.api.payment.entities.dtos.CommonUserDto;
import com.misael.api.payment.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository userRepository;

	public Object save(CommonUserDto dto) {
		if(userRepository.existsByCpf(dto.cpf()) ||
				userRepository.existsByEmail(dto.email())) {
			throw new RuntimeException();
		}
		User user = User.builder().completeName(dto.completeName())
				.password(dto.password())
				.email(dto.email())
				.cpf(dto.cpf())
				.build();
		return userRepository.save(user);
	}
	
	
	
}
