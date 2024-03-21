package com.nttdata.bootcamp.mstransactions.services.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcamp.mstransactions.models.documents.CustomerPersonal;
import com.nttdata.bootcamp.mstransactions.services.CustomerPersonalService;

import reactor.core.publisher.Mono;

@Service
public class CustomerPersonalServiceImpl implements CustomerPersonalService{

    @Autowired
    @Qualifier("customer-personal")
    private WebClient.Builder clientPersonal;

    
    @Override
    public Mono<CustomerPersonal> findByNumberDocument(String numberDocument) {
        return clientPersonal
            .build()
            .get().uri("/number-document/{numberDocument}",Map.of("numberDocument",numberDocument))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(CustomerPersonal.class);
    }
}
