package com.nttdata.bootcamp.mstransactions.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionRequestDto {

    private String numberAccount;
    private Double amount;
}
