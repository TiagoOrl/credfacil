package com.asm.credfacil.repository

import com.asm.credfacil.entity.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<Client, Long> {
}