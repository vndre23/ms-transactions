package com.nttdata.bootcamp.mstransactions.services;

import com.nttdata.bootcamp.mstransactions.models.documents.CustomerPersonal;

import reactor.core.publisher.Mono;

public interface CustomerPersonalService {

    public Mono<CustomerPersonal> findByNumberDocument(String numberDocument);
}
