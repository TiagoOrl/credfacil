package com.asm.credfacil.repository

import com.asm.credfacil.entity.Credit
import com.asm.credfacil.entity.Customer
import com.asm.credfacil.static_mocks.CreditMock
import com.asm.credfacil.static_mocks.CustomerMock
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test") // carregar configurações do application-test.properties
@DataJpaTest // indica que será um teste do repositório
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditRepositoryTest {
    @Autowired
    lateinit var repostory: CreditRepository
    @Autowired
    lateinit var testEntityManager: TestEntityManager

    private lateinit var customer: Customer
    private lateinit var credit1: Credit
    private lateinit var credit2: Credit

    @BeforeEach
    fun setup() {
        customer = testEntityManager.persist(CustomerMock.buildCustomer())
        credit1 = testEntityManager.persist(CreditMock.buildCredit(customer = customer))
        credit2 = testEntityManager.persist(CreditMock.buildCredit(customer = customer))
    }
}