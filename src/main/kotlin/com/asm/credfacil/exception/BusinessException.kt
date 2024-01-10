package com.asm.credfacil.exception

data class BusinessException(override val message: String?) : RuntimeException(message)