package com.ara.es

import com.ara.es.dto.AccountCreateDTO
import com.ara.es.dto.AccountDTO
import com.ara.es.service.AccountCommandService
import com.ara.es.service.AccountQueryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class CreateAccountSpec extends Specification {

    @Autowired
    AccountCommandService accountCommandService

    @Autowired
    AccountQueryService accountQueryService

    def 'should create new account and check if it is exist'() {
        given:
        def accountCreateDTO = new AccountCreateDTO()
        accountCreateDTO.setCurrency('DOLLARS')
        accountCreateDTO.setStartingBalance(1000)

        when:
        def id = accountCommandService.createAccount(accountCreateDTO)

        then:
        AccountDTO accountDTO = accountQueryService.getCurrentState(id)

        expect:
        accountDTO != null
    }

    def 'should create new account and check if accountBalance is proper'() {
        given:
        def accountCreateDTO = new AccountCreateDTO()
        accountCreateDTO.setCurrency('DOLLARS')
        accountCreateDTO.setStartingBalance(1000)

        when:
        def id = accountCommandService.createAccount(accountCreateDTO)

        then:
        AccountDTO accountDTO = accountQueryService.getCurrentState(id)

        expect:
        accountDTO.getAccountBalance() == accountCreateDTO.getStartingBalance()
    }
}
