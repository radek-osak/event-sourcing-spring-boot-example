package com.ara.es.es.commands;

import com.ara.es.es.BaseCommand;

public class DebitMoneyCommand extends BaseCommand<String> {

    public final double debitAmmount;

    public final String currency;

    public DebitMoneyCommand(String id, double debitAmmount, String currency) {
        super(id);
        this.debitAmmount = debitAmmount;
        this.currency = currency;
    }
}
