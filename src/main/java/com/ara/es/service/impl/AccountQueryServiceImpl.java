package com.ara.es.service.impl;

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
}
