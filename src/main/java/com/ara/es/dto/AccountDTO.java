package com.ara.es.dto;

import com.ara.es.utils.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

    private String id;
    private Status status;
    private double accountBalance;
}
