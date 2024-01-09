package com.asm.credfacil.service

import com.asm.credfacil.entity.Credit

interface ICreditService {
    fun save(credit: Credit): Credit
}