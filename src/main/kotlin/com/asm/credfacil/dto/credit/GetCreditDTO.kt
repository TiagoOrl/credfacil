package com.asm.credfacil.dto.credit

import com.asm.credfacil.entity.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

data class GetCreditDTO (
    val id: Long,
    val creditCode: UUID,
    var creditValue: BigDecimal,
    val dayFirstInstallment: LocalDate,
    var numOfInstallments: Int,
    val status: Status
)