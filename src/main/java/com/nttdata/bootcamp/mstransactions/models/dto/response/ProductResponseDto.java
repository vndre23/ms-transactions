package com.nttdata.bootcamp.mstransactions.models.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    
    private String name;
    private Double commission;
    private Double movementLimit;
    private Date transactionDate;
    private Date createdAt;
}
