package com.asm.credfacil.entity

import jakarta.persistence.*

@Embeddable // tabela embutida
data class Address(
    @Column(nullable = false)
    var zipCode: String,

    @Column(nullable = false)
    var addressLine: String,
)