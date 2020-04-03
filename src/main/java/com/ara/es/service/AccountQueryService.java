package com.ara.es.service;

import com.ara.es.dto.AccountDTO;

import java.util.List;

public interface AccountQueryService {

    List<Object> auditAccount(String id);

    AccountDTO getCurrentState(String id);
}
