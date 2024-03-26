package com.misael.api.payment.services;

import java.util.List;
import java.util.Optional;

import com.misael.api.payment.entities.CommonUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misael.api.payment.entities.dtos.CommonUserDto;
import com.misael.api.payment.entities.enums.UserType;
import com.misael.api.payment.exceptions.UserExistsException;
import com.misael.api.payment.repositories.CommonUserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommonUserService {

	@Autowired
	private CommonUserRepository commonUserRepository;
	
	
	public List<CommonUser> findAll() {
		return commonUserRepository.findAll();
	}

	public CommonUser saveNewCommonUser(CommonUserDto commonUserDto) {
		if(commonUserRepository.existsByCpf(commonUserDto.cpf()) ||
				commonUserRepository.existsByEmail(commonUserDto.email())) {
			throw new UserExistsException();
		}
		var user = CommonUser.builder()
				.completeName(commonUserDto.completeName())
				.password(commonUserDto.password())
				.email(commonUserDto.email())
				.cpf(commonUserDto.cpf())
				.wallet(commonUserDto.wallet())			
				.userType(UserType.COMMON)
				.build();
		
		return commonUserRepository.save(user);
	}

	public Optional<CommonUser> findById(Integer id){
		return commonUserRepository.findById(id);
	}
	
	
	
}
