package com.nttdata.bootcamp.mstransactions.controllers;


import java.util.ArrayList;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.mstransactions.models.documents.Transaction;
import com.nttdata.bootcamp.mstransactions.models.dto.request.TransactionRequestDto;
import com.nttdata.bootcamp.mstransactions.models.dto.response.PersonalProductResponseDto;
import com.nttdata.bootcamp.mstransactions.models.dto.response.ProductResponseDto;
import com.nttdata.bootcamp.mstransactions.models.dto.response.TransactionResponseDto;
import com.nttdata.bootcamp.mstransactions.services.CustomerPersonalService;
import com.nttdata.bootcamp.mstransactions.services.PersonalProductService;
import com.nttdata.bootcamp.mstransactions.services.ProductService;
import com.nttdata.bootcamp.mstransactions.services.TransactionService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private PersonalProductService personalProductService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CustomerPersonalService customerPersonalService;

    @Autowired
    private ProductService productService;


    @PostMapping("/pay-to")
    public Mono<ResponseEntity<PersonalProductResponseDto>> PayTo(@RequestBody TransactionRequestDto dto){
        
        PersonalProductResponseDto response = new PersonalProductResponseDto();
        return this.personalProductService.findByNumberAccount(dto.getNumberAccount())
                .flatMap(personalProduct -> {
                    Double beforeBalance = personalProduct.getBalance();
                    response.setBalance(dto.getAmount());
                    response.setNumberAccount(dto.getNumberAccount());
                    return this.personalProductService.updateBalance(personalProduct.getNumberAccount(),beforeBalance + dto.getAmount()); 
                }).flatMap(personalProduct -> {
                    Transaction tr = new Transaction();
                    tr.setAmount(dto.getAmount());
                    tr.setPersonalProductId(new ObjectId(personalProduct.getId()));
                    return this.transactionService.save(tr);
                })
                .map(p -> {
                    return ResponseEntity.ok(response);
                }).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // @GetMapping("/pay-too/{id}")
    // public Mono<ResponseEntity<Product>> prod(@PathVariable String id){
    //     return this.personalProductService.findById(id).map(dd-> {
    //         return ResponseEntity.ok(dd);
    //     });
    // }

    @PostMapping("/withdraw-To")
    public Mono<ResponseEntity<PersonalProductResponseDto>> withdrawTo(@RequestBody TransactionRequestDto dto){
        PersonalProductResponseDto response = new PersonalProductResponseDto();
        return this.personalProductService.findByNumberAccount(dto.getNumberAccount())
                .flatMap(personalProduct -> {
                    Double beforeBalance = (personalProduct.getBalance());
                    response.setBalance(dto.getAmount());
                    response.setNumberAccount(dto.getNumberAccount());
                    if(beforeBalance<dto.getAmount()){
                        return Mono.error(new IllegalArgumentException("insufficient balance"));
                    }else{
                        return this.personalProductService.updateBalance(personalProduct.getNumberAccount(),beforeBalance - dto.getAmount()); 
                    }
                    
                }).flatMap(personalProduct -> {
                    Transaction tr = new Transaction();
                    tr.setAmount(-dto.getAmount());
                    tr.setPersonalProductId(new ObjectId(personalProduct.getId()));
                    return this.transactionService.save(tr);
                })
                .map(p -> {
                    return ResponseEntity.ok(response);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @GetMapping("/numberDocument/{numberDocument}")
    public Mono<ResponseEntity<TransactionResponseDto>> findByNumberDocument(@PathVariable String numberDocument){
        TransactionResponseDto response = new TransactionResponseDto();
        List<PersonalProductResponseDto> personalProducts = new ArrayList<>();
        List<Transaction> transactionsListDto = new ArrayList<>();

        return this.customerPersonalService.findByNumberDocument(numberDocument)
            .flatMap(customerPersonal -> {
                response.setName(customerPersonal.getName());
                response.setLastname(customerPersonal.getLastname());
                response.setNumberDocument(customerPersonal.getNumberDocument());
                response.setAddress(customerPersonal.getAddress());
                response.setPhone(customerPersonal.getPhone());
                response.setId(customerPersonal.getId());
                return this.personalProductService.findByCustomerPersonalId(response.getId()).collectList()
                    .map(personalProductList -> {
                        
                        personalProductList.forEach(list -> {
                            
                            PersonalProductResponseDto personalP = new PersonalProductResponseDto();
                            personalP.setBalance(list.getBalance());
                            personalP.setCreateAt(list.getCreateAt());
                            personalP.setNumberAccount(list.getNumberAccount());
                            personalP.setProductId(list.getProductId().toString());
                            personalP.setId(list.getId());
                            personalP.setId(list.getId());
                            personalP.setCustomerPersonalId(list.getCustomerPersonalId().toString());
                            System.out.println("entra al 1 forech"+ personalP.getProductId());
                            
                            this.productService.findById(personalP.getProductId())
                                .map(product-> {
                                    System.out.println("entra al map del product");
                                    ProductResponseDto p = new ProductResponseDto();
                                    p.setName(product.getName());
                                    p.setCommission(product.getCommission());
                                    p.setMovementLimit(product.getMovementLimit());
                                    p.setCreatedAt(product.getCreatedAt());
                                    personalP.setProduct(p);
                                    
                                    return product;
                                });
                            this.transactionService.findByPersonalProductId(personalP.getCustomerPersonalId()).collectList()
                                .map(transactionList->{
                                    transactionList.forEach( tr -> {
                                        System.out.println("entra 2 foreach");
                                        Transaction transaction = new Transaction();
                                        transaction.setAmount(tr.getAmount());
                                        transaction.setId(tr.getId());
                                        transaction.setCreatedAt(tr.getCreatedAt());
                                        transaction.setPersonalProductId(tr.getPersonalProductId());
                                        transactionsListDto.add(tr);
                                        System.out.println("transactions->"+tr);
                                        
                                        
                                    });
                                personalP.setTransactions(transactionsListDto);
                                return transactionList;
                                
                            });
                            personalProducts.add(personalP);
                            
                                
                            
                        });
                        response.setPersonalProducts(personalProducts);
                        return personalProductList;
                        
                    });
                    // .map(personalProductList -> {
                    //     personalProductList.forEach( personalProd-> {
                    //         PersonalProductResponseDto personalP = new PersonalProductResponseDto();
                    //         personalP.setBalance(personalProd.getBalance());
                    //         personalP.setNumberAccount(personalProd.getNumberAccount());
                    //         personalP.setCreateAt(personalProd.getCreateAt());
                    //         personalP.setProductId(personalProd.getProductId().toString());

                    //         this.productService.findById(personalP.getProductId())
                    //             .map( product-> {
                    //                 ProductResponseDto prod = new ProductResponseDto();
                    //                 prod.setName(product.getName());
                    //                 prod.setCommission(product.getCommission());
                    //                 prod.setMovementLimit(product.getMovementLimit());
                    //                 prod.setCreatedAt(product.getCreatedAt());
                    //                 personalProducts.add(personalP);
                    //                 response.setPersonalProducts(personalProducts);
                    //                 return product;
                    //         }).block();
                    //     });
                    // });
            })
            .map( res -> ResponseEntity.ok(response));
    }
}
