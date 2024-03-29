package com.misael.api.payment.repositories;

import com.misael.api.payment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	boolean existsByEmail(String email);
	boolean existsByCpf(String cpf);
	boolean existsByCnpj(String cnpj);

}
