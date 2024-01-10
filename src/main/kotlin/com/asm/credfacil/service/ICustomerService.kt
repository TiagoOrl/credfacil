package com.asm.credfacil.service

import com.asm.credfacil.entity.Customer


interface ICustomerService {
    fun save(customer: Customer): Customer
    fun findById(id: Long): Customer
    fun delete(id: Long)
}