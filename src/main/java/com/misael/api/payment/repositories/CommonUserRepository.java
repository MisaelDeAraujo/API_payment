package com.misael.api.payment.repositories;

import com.misael.api.payment.entities.CommonUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonUserRepository extends JpaRepository<CommonUser, Integer>{

	boolean existsByEmail(String email);
	boolean existsByCpf(String cpf);

}
