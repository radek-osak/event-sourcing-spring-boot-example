package com.ara.es.service;

import com.ara.es.dto.AccountCreateDTO;
import com.ara.es.dto.MoneyCreditedDTO;
import com.ara.es.dto.MoneyDebitDTO;

public interface AccountCommandService {

    String createAccount(AccountCreateDTO accountCreateDTO);
    void creditMoneyToAccount(MoneyCreditedDTO moneyCreditedDTO);
    void debitMoneyFromAccount(MoneyDebitDTO moneyDebitDTO);
    void activateAccount(String id);
}
