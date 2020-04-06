package com.ara.es

import com.ara.es.dto.AccountCreateDTO
import com.ara.es.dto.AccountDTO
import com.ara.es.dto.MoneyCreditedDTO
import com.ara.es.dto.MoneyDebitDTO
import com.ara.es.service.AccountCommandService
import com.ara.es.service.AccountQueryService
import com.ara.es.utils.Status
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ChangeStatusSpec extends Specification {

    @Autowired
    AccountCommandService accountCommandService

    @Autowired
    AccountQueryService accountQueryService

    def 'should set NEW_USER status after account creation'() {
        given:
        def accountCreateDTO = new AccountCreateDTO()
        accountCreateDTO.setCurrency('DOLLARS')
        accountCreateDTO.setStartingBalance(1000)

        when:
        def id = accountCommandService.createAccount(accountCreateDTO)

        then:
        AccountDTO accountDTO = accountQueryService.getCurrentState(id)

        expect:
        accountDTO.getStatus() == Status.NEW_USER
    }

    def 'should change status from NEW_USER to ACTIVATED after account activation'() {
        given:
        def accountCreateDTO = new AccountCreateDTO()
        accountCreateDTO.setCurrency('DOLLARS')
        accountCreateDTO.setStartingBalance(1000)

        when:
        def id = accountCommandService.createAccount(accountCreateDTO)
        accountCommandService.activateAccount(id)

        then:
        AccountDTO accountDTO = accountQueryService.getCurrentState(id)

        expect:
        accountDTO.getStatus() == Status.ACTIVATED
    }

    def 'should set status to HOLD if user debited all his money or more'() {
        given:
        def accountCreateDTO = new AccountCreateDTO()
        accountCreateDTO.setCurrency('DOLLARS')
        accountCreateDTO.setStartingBalance(1000)

        when:
        def id = accountCommandService.createAccount(accountCreateDTO)
        accountCommandService.activateAccount(id)

        and:
        def command = new MoneyDebitDTO()
        command.id = id
        command.currency = 'DOLLARS'
        command.debitAmount = 1500

        accountCommandService.debitMoneyFromAccount(command)

        then:
        AccountDTO accountDTO = accountQueryService.getCurrentState(id)

        expect:
        accountDTO.getStatus() == Status.HOLD
    }

    def 'should set status to ACTIVATED from HOLd if user credited enough negative account balance'() {
        given:
        def accountCreateDTO = new AccountCreateDTO()
        accountCreateDTO.setCurrency('DOLLARS')
        accountCreateDTO.setStartingBalance(1000)

        when:
        def id = accountCommandService.createAccount(accountCreateDTO)
        accountCommandService.activateAccount(id)

        and:
        def command = new MoneyDebitDTO()
        command.id = id
        command.currency = 'DOLLARS'
        command.debitAmount = 1500

        accountCommandService.debitMoneyFromAccount(command)

        and:
        def creditedDTO = new MoneyCreditedDTO()
        creditedDTO.id = id
        creditedDTO.creditAmount = 1900
        creditedDTO.currency = 'DOLLARS'

        accountCommandService.creditMoneyToAccount(creditedDTO)

        then:
        def accountDTO = accountQueryService.getCurrentState(id)

        expect:
        accountDTO.getStatus() == Status.ACTIVATED

    }
}
