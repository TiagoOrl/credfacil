package com.asm.credfacil.service.impl


import com.asm.credfacil.entity.Customer
import com.asm.credfacil.exception.BusinessException
import com.asm.credfacil.repository.CustomerRepository
import com.asm.credfacil.service.ICustomerService
import org.springframework.stereotype.Service


@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer = this.customerRepository.save(customer)

    override fun findById(id: Long): Customer = this.customerRepository.findById(id)
        .orElseThrow{throw BusinessException("Id $id not found") }

    override fun delete(id: Long) {
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}










