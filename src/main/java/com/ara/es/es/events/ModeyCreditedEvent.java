package com.ara.es.es.events;

import com.ara.es.es.BaseEvent;

public class ModeyCreditedEvent extends BaseEvent<String> {

    public final double creditAmount;

    public final String currency;

    public ModeyCreditedEvent(String id, double creditAmount, String currency) {
        super(id);
        this.creditAmount = creditAmount;
        this.currency = currency;
    }
}
