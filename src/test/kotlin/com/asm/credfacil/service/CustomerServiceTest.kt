package com.asm.credfacil.service

import com.asm.credfacil.repository.CustomerRepository
import com.asm.credfacil.service.impl.CustomerService
import com.asm.credfacil.static_mocks.CustomerMock
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test") // usar perfil de teste
@ExtendWith(MockKExtension::class) // indica que será usado a lib MockK para "mock"
class CustomerServiceTest {
    @MockK
    lateinit var repository: CustomerRepository // mockar repositório

    @InjectMockKs
    lateinit var service: CustomerService // classe a ser testada

    @Test
    fun `should create a new customer` () {
        //given
        val fakeCustomer = CustomerMock.buildCustomer()

        // aplicar mock ao metodo save() do repository
        every { repository.save(any()) } returns fakeCustomer

        //when
        val output = service.save(fakeCustomer)

        //then
        Assertions.assertThat(output).isNotNull
        Assertions.assertThat(output).isEqualTo(fakeCustomer)
    }
}