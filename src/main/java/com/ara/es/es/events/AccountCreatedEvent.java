package com.ara.es.es.events;

import com.ara.es.es.BaseEvent;
import com.ara.es.utils.Status;

public class AccountCreatedEvent extends BaseEvent<String> {

    public final double accountBalance;

    public final String currency;

    public final Status status;

    public AccountCreatedEvent(String id, double accountBalance, String currency, Status status) {
        super(id);
        this.accountBalance = accountBalance;
        this.currency = currency;
        this.status = status;
    }
}
