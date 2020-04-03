package com.ara.es.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MoneyDebitDTO {

    private String id;
    private double debitAmount;
    private String currency;
}
