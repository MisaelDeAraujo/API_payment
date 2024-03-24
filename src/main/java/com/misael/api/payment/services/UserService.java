package com.misael.api.payment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misael.api.payment.entities.User;
import com.misael.api.payment.entities.dtos.CommonUserDto;
import com.misael.api.payment.entities.dtos.ShopkeeperUserDto;
import com.misael.api.payment.entities.dtos.UserTransactionDto;
import com.misael.api.payment.entities.enums.UserType;
import com.misael.api.payment.exceptions.UserExistsException;
import com.misael.api.payment.exceptions.UserNotFoundException;
import com.misael.api.payment.exceptions.UserTypeWithoutPermissionException;
import com.misael.api.payment.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public Object findAll() {
		return userRepository.findAll();
	}

	public Object registerNewCommonUser(CommonUserDto commonUserDto) {
		if(userRepository.existsByCpf(commonUserDto.cpf()) ||
				userRepository.existsByEmail(commonUserDto.email())) {
			throw new UserExistsException();
		}
		var user = User.builder()
				.completeName(commonUserDto.completeName())
				.password(commonUserDto.password())
				.email(commonUserDto.email())
				.cpf(commonUserDto.cpf())
				.wallet(commonUserDto.wallet())			
				.userType(UserType.COMMON)
				.build();
		
		return userRepository.save(user);
	}
	
	public Object registerNewShopkeeperUser(ShopkeeperUserDto shopkeeperUserDto) {
		if(userRepository.existsByCpf(shopkeeperUserDto.cnpj()) ||
				userRepository.existsByEmail(shopkeeperUserDto.email())) {
			throw new UserExistsException();
		}
		var user = User.builder()
				.completeName(shopkeeperUserDto.completeName())
				.password(shopkeeperUserDto.password())
				.email(shopkeeperUserDto.email())
				.cnpj(shopkeeperUserDto.cnpj())
				.wallet(shopkeeperUserDto.wallet())			
				.userType(UserType.SHOPKEEPER)
				.build();
		
		return userRepository.save(user);
	}
	
	public void carryOutTransaction(UserTransactionDto dto) {
		Optional<User> payerId = userRepository.findById(dto.payer());
		Optional<User> payeeId = userRepository.findById(dto.payee());
		
		if(payerId.isPresent() && payeeId.isPresent()) {
			var payer = payerId.get();
			
			if(payer.getUserType() == UserType.COMMON && payer.getWallet() >= dto.value()) {
				var payee = payeeId.get();
				
				Double subtraction = payer.getWallet();
				subtraction -= dto.value();
				payer.setWallet(subtraction);
				
				Double addition = payee.getWallet();
				addition +=  dto.value();
				payee.setWallet(addition);
				
				userRepository.save(payer);
				userRepository.save(payee);

			}else if(payer.getUserType() == UserType.SHOPKEEPER) {
				throw new UserTypeWithoutPermissionException(); 
			}
			
		}else {
			throw new UserNotFoundException();
			
		}
		
	}
	
	
	
	
}
