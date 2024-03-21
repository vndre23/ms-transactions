package com.nttdata.bootcamp.mstransactions.models.documents;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String id;
    private String name;
    private Double commission;
    private Double movementLimit;
    // @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date transactionDate;
    private Date createdAt;
}
