package com.asm.credfacil.service

import com.asm.credfacil.entity.Customer
import com.asm.credfacil.exception.BusinessException
import com.asm.credfacil.repository.CustomerRepository
import com.asm.credfacil.service.impl.CustomerService
import com.asm.credfacil.static_mocks.CustomerMock
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.util.Optional
import kotlin.random.Random

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
        verify (exactly = 1){ repository.save(fakeCustomer) } // verificar se o método save está sendo chamado apenas 1 vez
    }

    @Test
    fun `should return a customer by given Id` () {

        //given
        val fakeId = Random.nextLong()
        val fakeCustomer = CustomerMock.buildCustomer(id = fakeId)
        every { repository.findById(fakeId) } returns Optional.of(fakeCustomer)

        // when
        val found = service.findById(fakeId)

        //then
        Assertions.assertThat(found).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(found).isNotNull
        Assertions.assertThat(found).isSameAs(fakeCustomer)
        verify (exactly = 1) { repository.findById(fakeId) }
    }

    @Test
    fun `should throw customer not found error` () {
        //given
        val fakeId = 5L
        every { repository.findById(fakeId) } returns Optional.empty()

        //when

        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { service.findById(fakeId) }
            .withMessage("Id $fakeId not found")

        verify (exactly = 1) { repository.findById(fakeId) }
    }
}