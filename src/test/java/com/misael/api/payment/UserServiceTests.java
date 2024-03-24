package com.misael.api.payment;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.misael.api.payment.entities.User;
import com.misael.api.payment.entities.dtos.CommonUserDto;
import com.misael.api.payment.entities.dtos.ShopkeeperUserDto;
import com.misael.api.payment.entities.dtos.UserTransactionDto;
import com.misael.api.payment.repositories.UserRepository;
import com.misael.api.payment.services.UserService;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {

	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository repository;
	
	@Test
	public void shouldRegisterCommonUser() {
	
		CommonUserDto dto = new CommonUserDto("test", "test", "test@email.com", "43843013080", 1000.00);
		CommonUserDto dto2 = new CommonUserDto("test2", "test2", "test2@email.com", "38482504037", 1000.00);
		service.registerNewCommonUser(dto);
		service.registerNewCommonUser(dto2);
		
		Optional<User> findId = repository.findById(1);
		Optional<User> findId2 = repository.findById(2);
		
		assertTrue(findId.isPresent());
		assertTrue(findId2.isPresent());
	}
	
	@Test
	@Disabled
	public void shouldNotRegisterCommonUser() {
		CommonUserDto dto = new CommonUserDto("test", "test", "test@email.com", "43843013080", 1000.00);
		service.registerNewCommonUser(dto);	
		
	}
	
	@Test
	public void shouldRegisterShopkeeperUser() {
		ShopkeeperUserDto dto = new ShopkeeperUserDto("test3", "test3", "test3@email.com", "03453441000137", 1000.00);
		service.registerNewShopkeeperUser(dto);
		Optional<User> findId = repository.findById(3);
		assertTrue(findId.isPresent());
	}
	@Test
	@Disabled
	public void shouldNotRegisterShopkeeperUser() {
		ShopkeeperUserDto dto = new ShopkeeperUserDto("test", "test", "test@email.com", "03453441000137", 1000.00);
		service.registerNewShopkeeperUser(dto);
	}
	
	@Test
	public void shouldCarryOutTransaction() {
		UserTransactionDto dto = new UserTransactionDto(100.00, 1, 2);
		service.carryOutTransaction(dto);
		
		Optional<User> findId = repository.findById(1);

		var user = findId.get();
		boolean value = user.getWallet() == 900.00;
		assertTrue(value);
			
	}
	@Test
	@Disabled
	public void shouldNotCarryOutTransaction() {
		UserTransactionDto dto = new UserTransactionDto(100.00, 10, 1);
		service.carryOutTransaction(dto);
			
	}
	
}
