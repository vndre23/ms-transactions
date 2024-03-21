package com.nttdata.bootcamp.mstransactions.models.documents;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Transaction {

    @Id
    private String id;
    private Double amount;
    private Date createdAt;
    private ObjectId personalProductId;
    

}
