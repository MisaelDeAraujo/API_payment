package com.misael.api.payment.services;

import com.misael.api.payment.entities.CommonUser;
import com.misael.api.payment.entities.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserTransactionAuthorizationService {

    @Autowired
    private CommonUserService commonUserService;

    public boolean authorization(Double value,Integer id1, Integer id2) {
        Optional<CommonUser> payerId = commonUserService.findById(id1);
        Optional<CommonUser> payeeId = commonUserService.findById(id2);

        if (payerId.isPresent() && payeeId.isPresent()) {
            CommonUser payer = payerId.get();

            if (payer.getUserType() == UserType.COMMON && payer.getWallet() >= value
            && value > 0 && payer.getWallet() > 0) {
                return true;
            }

        }

        return false;
    }





}