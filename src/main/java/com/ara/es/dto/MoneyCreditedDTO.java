package com.ara.es.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MoneyCreditedDTO {

    private String id;
    private double creditAmount;
    private String currency;
}
