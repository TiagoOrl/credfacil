package com.asm.credfacil.repository

import com.asm.credfacil.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface CreditRepository : JpaRepository<Credit, Long> {
}