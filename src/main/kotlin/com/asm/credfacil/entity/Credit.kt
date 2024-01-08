package com.asm.credfacil.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "credit")
data class Credit(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val creditCode: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    var creditValue: BigDecimal,

    @Column(nullable = false)
    val dayFirstInstallment: LocalDate,

    @Column(nullable = false)
    var numOfInstallments: Int,

    @Enumerated // indica que é uma enumeração para o JPA
    val status: Status,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_client_id")
    var client: Client
)
