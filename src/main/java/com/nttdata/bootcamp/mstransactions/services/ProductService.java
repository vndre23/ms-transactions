package com.nttdata.bootcamp.mstransactions.services;

import com.nttdata.bootcamp.mstransactions.models.documents.Product;

import reactor.core.publisher.Mono;

public interface ProductService {

    public Mono<Product> findById(String id);
}
