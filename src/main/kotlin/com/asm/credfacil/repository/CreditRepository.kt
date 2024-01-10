package com.asm.credfacil.repository

import com.asm.credfacil.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface CreditRepository : JpaRepository<Credit, Long> {
    @Query("SELECT credit from Credit credit WHERE credit.client.id = ?1")
    fun findAllByClientId(clientId: Long): Optional<List<Credit>>

    fun findByCreditCode(code: UUID): Optional<Credit>
}