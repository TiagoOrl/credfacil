package com.asm.credfacil.repository

import com.asm.credfacil.entity.Credit
import com.asm.credfacil.entity.Customer
import com.asm.credfacil.static_mocks.CreditMock
import com.asm.credfacil.static_mocks.CustomerMock
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import java.util.*

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

    @Test
    fun `should return credit by code` () {
        //given
        val code1 = UUID.fromString("ef8872dd-135c-4dd3-a196-dc6d253223c1")
        val code2 = UUID.fromString("f0b65a1e-8b0b-4d93-a2f6-569b5a71539f")

        credit1.creditCode = code1
        credit2.creditCode = code2

        //when
        val fakeCredit1 = repostory.findByCreditCode(code1)
        val fakeCredit2 = repostory.findByCreditCode(code2)

        //then
        Assertions.assertThat(fakeCredit1).isNotNull
        Assertions.assertThat(fakeCredit1).isEqualTo(credit1)

    }

    @Test
    fun `should return credits list by customer Id` () {
        //given

        // when
        val output = repostory.findAllByCustomerId(customer.id!!)

        // then
        Assertions.assertThat(output).isNotEmpty
        Assertions.assertThat(output.size).isEqualTo(2)
        Assertions.assertThat(output).isEqualTo(
            listOf(
                credit1,
                credit2
            )
        )

    }
}