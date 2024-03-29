package com.misael.api.payment.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misael.api.payment.entities.User;
import com.misael.api.payment.entities.dtos.UserRequestDto;
import com.misael.api.payment.entities.dtos.UserResponseDto;
import com.misael.api.payment.entities.enums.UserType;
import com.misael.api.payment.exceptions.UserExistsException;
import com.misael.api.payment.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public List<UserResponseDto> findAll() {
		
		List<User> findAllUsers = userRepository.findAll();
		List<UserResponseDto> responseAllUser = new ArrayList<>();
		for (User user : findAllUsers) {
			if(user.getUserType() == UserType.COMMON) {
				UserResponseDto dto = UserResponseDto.builder()
						.completeName(user.getCompleteName())
						.email(user.getEmail())
						.wallet(user.getWallet())
						.type(UserType.COMMON)
						.build();
				responseAllUser.add(dto);
			}
			else if( user.getUserType() == UserType.MERCHANT) {
				UserResponseDto dto = UserResponseDto.builder()
						.completeName(user.getCompleteName())
						.email(user.getEmail())
						.wallet(user.getWallet())
						.type(UserType.MERCHANT)
						.build();
				
				responseAllUser.add(dto);
			}
			else {
				throw new RuntimeException();
			}
			
		
		}
		
		return responseAllUser;
	}

	public UserResponseDto saveNewUser(UserRequestDto dto) {
		if(userRepository.existsByCpf(dto.document()) ||
				userRepository.existsByEmail(dto.email()) ||
				userRepository.existsByCnpj(dto.document())) { //cnpj
			throw new UserExistsException();
		}
		
		if(dto.document().length() == 14) {
			User merchantUser = User.builder()
					.completeName(dto.completeName())
					.password(dto.password())
					.email(dto.email())
					.cnpj(dto.document())
					.wallet(dto.wallet())
					.userType(UserType.MERCHANT)
					.build();
			userRepository.save(merchantUser);
			UserResponseDto responseDto = UserResponseDto.builder()
					.completeName(dto.completeName())
					.email(dto.email())
					.wallet(dto.wallet())
					.type(UserType.MERCHANT)
					.build();
			return responseDto;
		}
		if(dto.document().length() == 11) {
			User commonUser = User.builder()
					.completeName(dto.completeName())
					.password(dto.password())
					.email(dto.email())
					.cpf(dto.document())
					.wallet(dto.wallet())
					.userType(UserType.COMMON)
					.build();
			userRepository.save(commonUser);
			UserResponseDto responseDto = UserResponseDto.builder()
					.completeName(dto.completeName())
					.email(dto.email())
					.wallet(dto.wallet())
					.type(UserType.COMMON)
					.build();
			
			return responseDto;
		}
		
		throw new RuntimeException();
		
	}

	public Optional<User> findById(Integer id){
		return userRepository.findById(id);
	}
	
	
	
}
