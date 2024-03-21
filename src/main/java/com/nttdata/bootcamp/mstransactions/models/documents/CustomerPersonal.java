package com.nttdata.bootcamp.mstransactions.models.documents;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPersonal {

    private String id;
    private String numberDocument;
    private String name;
    private String lastname;
    private String address;
    private String phone;
    private Date createAt;
}
