package com.nttdata.bootcamp.mstransactions.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.mstransactions.models.documents.Transaction;

import reactor.core.publisher.Flux;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String>{

    public Flux<Transaction> findByPersonalProductId (String customerPersonalId);
}
