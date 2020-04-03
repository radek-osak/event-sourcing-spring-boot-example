package com.ara.es

import com.ara.es.dto.AccountCreateDTO
import com.ara.es.dto.MoneyCreditedDTO
import com.ara.es.service.AccountCommandService
import com.ara.es.service.AccountQueryService
import com.ara.es.utils.Status
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class CreditMoneySpec extends Specification {

    @Autowired
    AccountCommandService accountCommandService

    @Autowired
    AccountQueryService accountQueryService

    def 'should credit amount to account balance'() {
        given:
        def accountCreateDTO = new AccountCreateDTO()
        accountCreateDTO.setCurrency('DOLLARS')
        accountCreateDTO.setStartingBalance(1000)
        def id = accountCommandService.createAccount(accountCreateDTO)

        when:
        def creditedDTO = new MoneyCreditedDTO()
        creditedDTO.id = id
        creditedDTO.creditAmount = 50
        creditedDTO.currency = 'PLN'

        accountCommandService.creditMoneyToAccount(creditedDTO)

        then:
        def accountDTO = accountQueryService.getCurrentState(id)

        expect:
        accountDTO.status == Status.ACTIVATED
        accountDTO.accountBalance == 1050
    }
}
