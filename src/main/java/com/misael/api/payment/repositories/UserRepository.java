package com.misael.api.payment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misael.api.payment.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	boolean existsByEmail(String email);
	boolean existsByCpf(String cpf);
	boolean existsByCnpj(String cnpj);
}
