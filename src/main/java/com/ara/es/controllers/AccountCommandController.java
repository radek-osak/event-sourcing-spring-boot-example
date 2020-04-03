package com.ara.es.controllers;

import com.ara.es.dto.AccountCreateDTO;
import com.ara.es.dto.MoneyCreditedDTO;
import com.ara.es.dto.MoneyDebitDTO;
import com.ara.es.service.AccountCommandService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/command")
public class AccountCommandController {

    private final AccountCommandService accountCommandService;

    public AccountCommandController(AccountCommandService accountCommandService) {
        this.accountCommandService = accountCommandService;
    }

    @PostMapping("/create")
    public void createAccount(@RequestBody AccountCreateDTO accountCreateDTO) {
        accountCommandService.createAccount(accountCreateDTO);
    }

    @PutMapping("/credit")
    public void creditToAccount(@RequestBody MoneyCreditedDTO moneyCreditedDTO) {
        accountCommandService.creditMoneyToAccount(moneyCreditedDTO);
    }

    @PutMapping("/debit")
    public void debitFromAccount(@RequestBody MoneyDebitDTO moneyDebitDTO) {
        accountCommandService.debitMoneyFromAccount(moneyDebitDTO);
    }
}
