package com.ara.es.service.impl;

import com.ara.es.dto.AccountCreateDTO;
import com.ara.es.dto.MoneyCreditedDTO;
import com.ara.es.dto.MoneyDebitDTO;
import com.ara.es.es.commands.CreateAccountCommand;
import com.ara.es.es.commands.CreditMoneyCommand;
import com.ara.es.es.commands.DebitMoneyCommand;
import com.ara.es.service.AccountCommandService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {

    private final CommandGateway commandGateway;

    public AccountCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public void createAccount(AccountCreateDTO accountCreateDTO) {
        commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(), accountCreateDTO.getStartingBalance(), accountCreateDTO.getCurrency()));
    }

    @Override
    public void creditMoneyToAccount(MoneyCreditedDTO moneyCreditedDTO) {
        commandGateway.send(new CreditMoneyCommand(moneyCreditedDTO.getId(), moneyCreditedDTO.getCreditAmount(), moneyCreditedDTO.getCurrency()));
    }

    @Override
    public void debitMoneyFromAccount(MoneyDebitDTO moneyDebitDTO) {
        commandGateway.send(new DebitMoneyCommand(moneyDebitDTO.getId(), moneyDebitDTO.getDebitAmount(), moneyDebitDTO.getCurrency()));
    }
}
