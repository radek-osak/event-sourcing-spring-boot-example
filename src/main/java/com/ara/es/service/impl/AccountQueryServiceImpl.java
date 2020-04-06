package com.ara.es.service.impl;

import com.ara.es.dto.AccountDTO;
import com.ara.es.es.events.*;
import com.ara.es.service.AccountQueryService;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountQueryServiceImpl implements AccountQueryService {

    private final EventStore eventStore;

    public AccountQueryServiceImpl(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public List<Object> auditAccount(String id) {
        return eventStore
                .readEvents(id)
                .asStream()
                .map(o -> o.getPayload())
                .collect(Collectors.toList());
    }

    @Override
    public AccountDTO getCurrentState(String id) {
        AccountDTO accountDTO = new AccountDTO();
        List<Object> events = auditAccount(id);
        events.forEach(o -> {
            if (o instanceof AccountCreatedEvent) {
                accountDTO.setId(((AccountCreatedEvent) o).id);
                accountDTO.setAccountBalance(((AccountCreatedEvent) o).accountBalance);
                accountDTO.setStatus(((AccountCreatedEvent) o).status);
            }
            if (o instanceof AccountActivatedEvent) {
                accountDTO.setStatus(((AccountActivatedEvent) o).status);
            }
            if (o instanceof AccountHeldEvent) {
                accountDTO.setStatus(((AccountHeldEvent) o).status);
            }
            if (o instanceof MoneyDebitedEvent) {
                accountDTO.setAccountBalance(accountDTO.getAccountBalance() - ((MoneyDebitedEvent) o).debitAmount);
            }
            if (o instanceof MoneyCreditedEvent) {
                accountDTO.setAccountBalance(accountDTO.getAccountBalance() + ((MoneyCreditedEvent) o).creditAmount);
            }
        });
        return accountDTO;
    }
}
