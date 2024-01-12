package com.asm.credfacil.controller

import com.asm.credfacil.repository.CustomerRepository
import com.asm.credfacil.static_mocks.CustomerMock
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc // para mockar requests
@ContextConfiguration
class CustomerControllerTest {
    // para adicionar dados de teste no BD
    @Autowired
    private lateinit var repository: CustomerRepository
    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var objectMapper: ObjectMapper // usado para serialização de objectos Java

    val url = "/api/client"

    @BeforeEach
    fun setup() = repository.deleteAll()

    @AfterEach
    fun tearDown() = repository.deleteAll()

    @Test
    fun `should save entity and return body response`() {
        //given
        val customerDto = CustomerMock.getCustomerDTO()

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customerDto)))
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Cami"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Cavalcante"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("28475934625"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("camila@email.com"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.income").value("1000.0"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.zipCode").value("000000"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("Rua da Cami, 123"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andDo(MockMvcResultHandlers.print())


    }

    @Test
    fun `should try to save customer with existing cpf and return conflict status` () {
        //given
        repository.save(CustomerMock.getCustomerDTO().toEntity())
        val customerDto = CustomerMock.getCustomerDTO()

        //when
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDto)))
            .andExpect(MockMvcResultMatchers.status().isConflict)
            .andDo(MockMvcResultHandlers.print())
//            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(409))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("client with this cpf already exists"))

    }

}