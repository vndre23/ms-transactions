package com.nttdata.bootcamp.mstransactions.services.impl;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcamp.mstransactions.models.documents.PersonalProduct;
import com.nttdata.bootcamp.mstransactions.services.PersonalProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonalProductServiceImpl implements PersonalProductService{

    @Autowired
    @Qualifier("personal-product")
    private WebClient.Builder clientPersonalProduct;
    
    

    @Override
    public Mono<PersonalProduct> findByNumberAccount(String numberAccount) {
        
        return clientPersonalProduct
            .build()
            .get().uri("/number-account/{numberAccount}",Map.of("numberAccount",numberAccount))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(PersonalProduct.class);
    }

    

    



    @Override
    public Mono<PersonalProduct> updateBalance(String numberAccount, Double balance) {
        return clientPersonalProduct.build().put()
            .uri("/add-balance/{numberAccount}",Collections.singletonMap("numberAccount", numberAccount))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(balance))
            .retrieve()
            .bodyToMono(PersonalProduct.class);
    }



    @Override
    public Flux<PersonalProduct> findByCustomerPersonalId(String customerPersonalId) {
        return clientPersonalProduct
            .build()
            .get().uri("/customer-personal/{customerPersonalId}",Map.of("customerPersonalId",customerPersonalId))
            .accept(MediaType.APPLICATION_JSON)
            .exchangeToFlux(response -> response.bodyToFlux(PersonalProduct.class));
            // .retrieve()
            // .flux(PersonalProduct.class);
    }
}
