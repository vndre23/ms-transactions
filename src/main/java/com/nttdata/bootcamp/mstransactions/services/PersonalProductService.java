package com.nttdata.bootcamp.mstransactions.services;

import com.nttdata.bootcamp.mstransactions.models.documents.PersonalProduct;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonalProductService {

    public Mono<PersonalProduct> findByNumberAccount(String numberAccount);

    public Mono<PersonalProduct> updateBalance(String numberAccount, Double balance);

    public Flux<PersonalProduct> findByCustomerPersonalId(String customerPersonalId);
}
