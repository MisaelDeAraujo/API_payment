package com.misael.api.payment.services;

import com.misael.api.payment.entities.CommonUser;
import com.misael.api.payment.entities.Transaction;
import com.misael.api.payment.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserTransactionAuthorizationService userTransactionAuthorizationService;
    @Autowired
    private CommonUserService commonUserService;

    public Transaction carryOutTransaction(double value, int payerId, int payeeId){
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
            return transactionRepository.save(transaction);
        }else{
            throw new RuntimeException();
        }
    }




}





