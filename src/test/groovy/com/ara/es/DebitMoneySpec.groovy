package com.ara.es

import com.ara.es.dto.AccountCreateDTO
import com.ara.es.dto.MoneyDebitDTO
import com.ara.es.service.AccountCommandService
import com.ara.es.service.AccountQueryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class DebitMoneySpec extends Specification {

    @Autowired
    AccountCommandService accountCommandService

    @Autowired
    AccountQueryService accountQueryService

    def 'should debit money if account balance is grater than zero'() {
        given:
        def accountCreateDTO = new AccountCreateDTO()
        accountCreateDTO.setCurrency('DOLLARS')
        accountCreateDTO.setStartingBalance(1000)
        def id = accountCommandService.createAccount(accountCreateDTO)

        when:
        def command = new MoneyDebitDTO()
        command.id = id
        command.currency = 'DOLLARS'
        command.debitAmount = 500

        accountCommandService.debitMoneyFromAccount(command)

        then:
        def accountDTO = accountQueryService.getCurrentState(id)

        expect:
        accountDTO.accountBalance == 500
    }

    def 'should debit money if account grater than zero but less than debit amount'() {
        given:
        def accountCreateDTO = new AccountCreateDTO()
        accountCreateDTO.setCurrency('DOLLARS')
        accountCreateDTO.setStartingBalance(1000)
        def id = accountCommandService.createAccount(accountCreateDTO)

        when:
        def command = new MoneyDebitDTO()
        command.id = id
        command.currency = 'DOLLARS'
        command.debitAmount = 1500

        accountCommandService.debitMoneyFromAccount(command)

        then:
        def accountDTO = accountQueryService.getCurrentState(id)

        expect:
        accountDTO.accountBalance == -500
    }
}
