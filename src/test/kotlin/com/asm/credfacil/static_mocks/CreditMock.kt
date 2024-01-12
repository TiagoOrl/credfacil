package com.asm.credfacil.static_mocks

import com.asm.credfacil.entity.Credit
import com.asm.credfacil.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

object CreditMock {
    fun buildCredit(
        creditValue: BigDecimal = BigDecimal.valueOf(100.0),
        dayFirstInstallment: LocalDate = LocalDate.now().plusMonths(2L),
        numberOfInstallments: Int = 15,
        customer: Customer = CustomerMock.buildCustomer()
    ): Credit = Credit(
        creditValue = creditValue,
        dayFirstInstallment = dayFirstInstallment,
        numberOfInstallments = numberOfInstallments,
        customer = customer
    )
}