package com.asm.credfacil.dto.client

import com.asm.credfacil.dto.credit.GetCreditDTO
import com.asm.credfacil.entity.Address
import java.math.BigDecimal
import java.util.*

data class GetClientDTO (
    val id: Long,
    var firstName: String,
    var lastName: String,
    var cpf: String,
    var email: String,
    var password: String,
    var income: BigDecimal,
    var createdAt: Date,
    val address: Address,
    val credits: Set<GetCreditDTO>
)