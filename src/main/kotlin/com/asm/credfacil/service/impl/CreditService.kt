package com.asm.credfacil.service.impl

import com.asm.credfacil.dto.credit.AddCreditDTO
import com.asm.credfacil.dto.credit.GetCreditDTO
import com.asm.credfacil.entity.Credit
import com.asm.credfacil.repository.ClientRepository
import com.asm.credfacil.repository.CreditRepository
import com.asm.credfacil.service.ICreditService
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.util.*

class CreditService(
    private val repository: CreditRepository,
    private val clientRepository: ClientRepository,
    private val mapper: ModelMapper
) : ICreditService {
    override fun save(credit: AddCreditDTO): AddCreditDTO {
        val clientFound = clientRepository.findById(credit.clientId)
        if (clientFound.isEmpty)
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Client with id ${credit.clientId} not found")

        val newCredit = mapper.map(credit, Credit::class.java)
        newCredit.apply {
            client = clientFound.get()
        }
        repository.save(newCredit)

        return credit
    }

    override fun findAllByCustomerId(customerId: Long): List<GetCreditDTO> {
        val found = repository.findAllByClientId(customerId)

        if (found.isEmpty)
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Found no credit for this client $customerId")

        return found.get().map {
            mapper.map(it, GetCreditDTO::class.java)
        }
    }

    override fun findByCreditCode(customerId: Long, code: UUID): GetCreditDTO {
        val found = repository.findByCreditCode(code)
        if (found.isEmpty)
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "credit with this code $code not found")

        if (found.get().client?.id != customerId)
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "can't access the credit of this user")

        return mapper.map(found.get(), GetCreditDTO::class.java)
    }
}