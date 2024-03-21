package com.nttdata.bootcamp.mstransactions.models.dto.response;

import java.util.Date;
import java.util.List;


import com.nttdata.bootcamp.mstransactions.models.documents.Transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalProductResponseDto {

    private String id;
    private String customerPersonalId;
    private String productId;
    private String numberAccount;
    private Double balance;
    private Date createAt;
    private Double remainingMovesLimit;
    private List<Transaction> transactions;
    private ProductResponseDto product;
}
