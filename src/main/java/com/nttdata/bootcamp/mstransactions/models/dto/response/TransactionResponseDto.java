package com.nttdata.bootcamp.mstransactions.models.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TransactionResponseDto {

    private String id;
    private String numberDocument;
    private String name;
    private String lastname;
    private String address;
    private String phone;

    private List<PersonalProductResponseDto> personalProducts;
}
