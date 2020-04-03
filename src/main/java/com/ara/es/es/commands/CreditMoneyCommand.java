package com.ara.es.es.commands;

import com.ara.es.es.BaseCommand;

public class CreditMoneyCommand extends BaseCommand<String> {

    public final double creditAmmount;

    public final String currency;

    public CreditMoneyCommand(String id, double creditAmmount, String currency) {
        super(id);
        this.creditAmmount = creditAmmount;
        this.currency = currency;
    }
}
