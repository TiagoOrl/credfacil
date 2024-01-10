package com.asm.credfacil.service.impl

import com.asm.credfacil.dto.client.AddCustomerDTO
import com.asm.credfacil.dto.client.GetClientDTO
import com.asm.credfacil.entity.Customer
import com.asm.credfacil.repository.ClientRepository
import com.asm.credfacil.service.IClientService
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ClientService(
    private val repository: ClientRepository,
    private val modelMapper: ModelMapper
): IClientService {

    override fun save(clientDTO: AddCustomerDTO): AddCustomerDTO {
        repository.save(clientDTO.toEntity())
        return clientDTO
    }

    override fun findById(clientId: Long): GetClientDTO {
        val foundClient = repository.findById(clientId)
        if (foundClient.isEmpty)
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "client with id $clientId not found")

        return modelMapper.map(foundClient.get(), GetClientDTO::class.java)
    }

    override fun delete(id: Long): GetClientDTO {
        val foundClient = repository.findById(id)

        if (foundClient.isEmpty)
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "client with id $id not found")

        repository.delete(foundClient.get())

        return modelMapper.map(foundClient, GetClientDTO::class.java)
    }
}










