package com.asm.credfacil.service.impl


import com.asm.credfacil.entity.Customer
import com.asm.credfacil.exception.BusinessException
import com.asm.credfacil.repository.CustomerRepository
import com.asm.credfacil.service.ICustomerService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer {
        customerRepository.findByCpf(customer.cpf).ifPresent {
            throw ResponseStatusException(HttpStatus.CONFLICT, "client with this cpf already exists")
        }
        customerRepository.findByEmail(customer.email).ifPresent {
            throw ResponseStatusException(HttpStatus.CONFLICT, "client with this email already exists")
        }

        return this.customerRepository.save(customer)
    }

    override fun findById(id: Long): Customer = this.customerRepository.findById(id)
        .orElseThrow{throw BusinessException("Id $id not found") }

    override fun delete(id: Long) {
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}










