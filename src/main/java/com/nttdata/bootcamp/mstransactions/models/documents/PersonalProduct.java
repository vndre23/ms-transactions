package com.nttdata.bootcamp.mstransactions.models.documents;

import java.util.Date;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalProduct {

    private String id;
    private ObjectId customerPersonalId;
    private ObjectId productId;
    private String numberAccount;
    private Double balance;
    private Date createAt;
    private Double remainingMovesLimit;
}
