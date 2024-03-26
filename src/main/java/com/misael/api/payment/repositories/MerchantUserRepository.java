package com.misael.api.payment.repositories;

import com.misael.api.payment.entities.MerchantUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantUserRepository extends JpaRepository<MerchantUser,Integer> {
}
