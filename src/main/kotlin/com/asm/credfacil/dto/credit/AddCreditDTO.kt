package com.asm.credfacil.dto.credit


import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.math.BigDecimal

data class AddCreditDTO (

    @NotNull
    var creditValue: BigDecimal,

    @NotNull(message = "dob is null")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    val dayFirstInstallment: String,

    @NotNull
    @Size
    var numOfInstallments: Int,

    @NotNull
    val clientId: Long
) {

}