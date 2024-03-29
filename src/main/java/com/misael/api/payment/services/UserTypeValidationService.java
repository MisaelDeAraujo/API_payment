package com.misael.api.payment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misael.api.payment.entities.User;
import com.misael.api.payment.entities.enums.UserType;
@Service
public class UserTypeValidationService {

    @Autowired
    private UserService userService;

    public boolean validateUserTypeTransaction(Double value,Integer id1, Integer id2) {
        Optional<User> payerId = userService.findById(id1);
        Optional<User> payeeId = userService.findById(id2);
        boolean result = false;

        if (payerId.isPresent() && payeeId.isPresent()) {
            User payer = payerId.get();
            if (payer.getUserType() == UserType.COMMON && payer.getWallet() >= value
            && value > 0 && payer.getWallet() > 0) {
                result = true;
            }

        }
        return result;

    }





}