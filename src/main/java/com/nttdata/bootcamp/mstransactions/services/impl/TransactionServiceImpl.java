package com.nttdata.bootcamp.mstransactions.services.impl;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.nttdata.bootcamp.mstransactions.models.documents.Transaction;
import com.nttdata.bootcamp.mstransactions.repositories.TransactionRepository;
import com.nttdata.bootcamp.mstransactions.services.TransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Mono<Transaction> save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Flux<Transaction> findByPersonalProductId(String PersonalProductId) {
        return transactionRepository.findByPersonalProductId(PersonalProductId);
    }

}
