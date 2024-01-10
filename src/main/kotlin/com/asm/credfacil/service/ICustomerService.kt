package com.asm.credfacil.service

import com.asm.credfacil.dto.client.AddCustomerDTO
import com.asm.credfacil.dto.client.GetClientDTO

interface IClientService {
    fun save(clientDTO: AddCustomerDTO): AddCustomerDTO
    fun findById(clientId: Long): GetClientDTO
    fun delete(id: Long): GetClientDTO
}