package com.asm.credfacil.service

import com.asm.credfacil.dto.credit.AddCreditDTO
import com.asm.credfacil.dto.credit.GetCreditDTO
import java.util.*

interface ICreditService {
    fun save(credit: AddCreditDTO): AddCreditDTO
    fun findAllByCustomerId(customerId: Long): List<GetCreditDTO>
    fun findByCreditCode(customerId: Long, code: UUID): GetCreditDTO
}