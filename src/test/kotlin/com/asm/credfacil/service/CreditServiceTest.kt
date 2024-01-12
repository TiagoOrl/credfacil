package com.asm.credfacil.service

import com.asm.credfacil.repository.CreditRepository
import com.asm.credfacil.service.impl.CreditService
import com.asm.credfacil.static_mocks.CreditMock
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles


@ActiveProfiles("test") // usar perfil de teste
@ExtendWith(MockKExtension::class) // indica que será usado a lib MockK para "mock"
class CreditServiceTest {
    @MockK
    lateinit var repository: CreditRepository // mock repository

    @InjectMockKs
    lateinit var service: CreditService // classe a ser testada


    @Test
    fun `should create a new credit for customer` () {
        //given
        val credit = CreditMock.buildCredit()
        every { repository.save(any()) } returns credit

        // when
        val output = service.save(credit)

        // then
        Assertions.assertThat(output).isNotNull
        Assertions.assertThat(output).isEqualTo(credit)
        verify (exactly = 1) { repository.save(any()) }
    }

    @Test
    fun `should return all credits by given customer Id` () {
        //given
        val customerId = 10L
        val credits = listOf(
            CreditMock.buildCredit(),
            CreditMock.buildCredit()
        )
        every { repository.findAllByCustomerId(any()) } returns credits

        // when
        val output = service.findAllByCustomer(customerId)

        // then
        Assertions.assertThat(output).isNotNull
        Assertions.assertThat(output).isEqualTo(credits)
        verify (exactly = 1) { repository.findAllByCustomerId(customerId) }

    }


}