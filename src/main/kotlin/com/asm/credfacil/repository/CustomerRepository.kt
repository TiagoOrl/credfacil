package com.asm.credfacil.repository

import com.asm.credfacil.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CustomerRepository : JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.cpf = ?1")
    fun findByCpf(cpf: String) : Optional<Customer>

    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    fun findByEmail(email: String): Optional<Customer>
}