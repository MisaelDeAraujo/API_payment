package com.misael.api.payment.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.misael.api.payment.entities.Transaction;
import com.misael.api.payment.entities.User;
import com.misael.api.payment.entities.dtos.UserTransactionResponseDto;
import com.misael.api.payment.exceptions.UnauthorizedTransactionException;
import com.misael.api.payment.repositories.TransactionRepository;
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserTypeValidationService userTransactionAuthorizationService;
    @Autowired
    private UserService commonUserService;
    @Autowired
    private RestTemplate restTemplate;

    public UserTransactionResponseDto carryOutTransaction(double value, int payerId, int payeeId){
        Optional<User> findPayer = commonUserService.findById(payerId);
        Optional<User> findPayee = commonUserService.findById(payeeId);
        if(userTransactionAuthorizationService.validateUserTypeTransaction(value, payerId, payeeId)){
            User payer = findPayer.get();
            User payee = findPayee.get();

            Double subtraction = payer.getWallet();
            subtraction -= value;
            payer.setWallet(subtraction);

            Double addition = payee.getWallet();
            addition += value;
            payee.setWallet(addition);

            Transaction transaction = Transaction.builder()
                    .payer(payer)
                    .payee(payee)
                    .localDateTime(LocalDateTime.now())
                    .build();
            
            ResponseEntity<Boolean> validateUserTypeTransaction = restTemplate
            		.getForEntity("http://localhost:8081/", Boolean.class);
            
            if(validateUserTypeTransaction.getBody() == true) {
            	transactionRepository.save(transaction);
            	boolean result = true;
            	UserTransactionResponseDto dto = UserTransactionResponseDto.builder()
            			.transactionMade(result)
            			.build();
            	return dto;
            }else {
            	boolean result = false;
            	UserTransactionResponseDto dto = UserTransactionResponseDto.builder()
            			.transactionMade(result)
            			.build();
            	return dto;
            }
            
        }else{
            throw new UnauthorizedTransactionException();
        }
    }




}





