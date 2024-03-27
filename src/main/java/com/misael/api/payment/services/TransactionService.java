package com.misael.api.payment.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.misael.api.payment.entities.CommonUser;
import com.misael.api.payment.entities.Transaction;
import com.misael.api.payment.exceptions.TransactionAuthorizationException;
import com.misael.api.payment.repositories.TransactionRepository;
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserTransactionAuthorizationService userTransactionAuthorizationService;
    @Autowired
    private CommonUserService commonUserService;
    @Autowired
    private RestTemplate restTemplate;

    public Object carryOutTransaction(double value, int payerId, int payeeId){
        Optional<CommonUser> findPayer = commonUserService.findById(payerId);
        Optional<CommonUser> findPayee = commonUserService.findById(payeeId);
        if(userTransactionAuthorizationService.authorization(value, payerId, payeeId)){
            CommonUser payer = findPayer.get();
            CommonUser payee = findPayee.get();

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
            
            ResponseEntity<String> response = restTemplate
            		.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", String.class);
            String external =  response.getBody();
            transactionRepository.save(transaction);
            return external; 
            
            
            
        }else{
            throw new TransactionAuthorizationException();
        }
    }




}





