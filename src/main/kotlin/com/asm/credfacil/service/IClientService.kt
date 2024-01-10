package com.asm.credfacil.service

import com.asm.credfacil.dto.client.AddClientDTO
import com.asm.credfacil.dto.client.GetClientDTO

interface IClientService {
    fun save(clientDTO: AddClientDTO): AddClientDTO
    fun findById(clientId: Long): GetClientDTO
    fun delete(id: Long): GetClientDTO
}