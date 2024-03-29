package com.misael.api.payment.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.misael.api.payment.entities.Transaction;
import com.misael.api.payment.entities.User;
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

    public String carryOutTransaction(double value, int payerId, int payeeId){
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
            
            ResponseEntity<String> externalApiAuthorizer = restTemplate
            		.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", String.class);
            
            if(externalApiAuthorizer.getBody() != null ) {
            	transactionRepository.save(transaction);
            ResponseEntity<String> sms = restTemplate
            		.getForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", String.class);
            	return sms.getBody();
            }else {
            	return "Transação não autorizada";
            }
            
        }else{
            throw new UnauthorizedTransactionException();
        }
    }




}





