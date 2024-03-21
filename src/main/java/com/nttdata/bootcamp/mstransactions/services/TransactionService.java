package com.nttdata.bootcamp.mstransactions.services;

import com.nttdata.bootcamp.mstransactions.models.documents.Transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

    
    public Mono<Transaction> save(Transaction transaction);

    public Flux<Transaction> findByPersonalProductId(String PersonalProductId);
}
