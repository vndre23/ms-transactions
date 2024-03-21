package com.nttdata.bootcamp.mstransactions.services.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcamp.mstransactions.models.documents.Product;
import com.nttdata.bootcamp.mstransactions.services.ProductService;

import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    @Qualifier("product")
    private WebClient.Builder clientProduct;

    @Override
    public Mono<Product> findById(String id) {
        return clientProduct
            .build()
            .get().uri("/{id}",Map.of("id",id))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Product.class);
    }
}
