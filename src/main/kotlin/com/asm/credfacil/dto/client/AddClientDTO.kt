package com.asm.credfacil.dto.client


import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

import java.math.BigDecimal

data class AddClientDTO (
    @NotBlank(message = "field required")
    @Size(min = 2, max = 50, message = "invalid firstName")
    var firstName: String,

    @NotBlank(message = "field required")
    @Size(min = 2, max = 50, message = "invalid lastName")
    var lastName: String,

    @NotBlank(message = "cpf is required")
    @Size(min = 5, max = 30)
    var cpf: String,

    @NotBlank(message = "email is required")
    @Email
    var email: String,

    @NotBlank(message = "password is required")
    @Size(min = 8, max = 120, message = "invalid password length")
    var password: String,

    @NotNull(message = "income is required")
    var income: BigDecimal,

    @NotBlank(message = "zipcode is required")
    @Size(min = 8, max = 8, message = "invalid zipcode")
    val zipCode: String,

    @NotBlank(message = "address line is required")
    @Size(min = 5, max = 40)
    val addressLine: String
)