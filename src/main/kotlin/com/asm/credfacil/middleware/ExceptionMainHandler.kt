package com.asm.credfacil.middleware

import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionMainHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleValidationExceptions(
        ex: MethodArgumentNotValidException
    ): Map<String, String?> {
        var errors = HashMap<String, String?>()
        ex.bindingResult.allErrors.forEach {
            val fieldName = (it as FieldError).field
            val message = it.defaultMessage
            errors.put(fieldName, message)

        }

        return errors
    }
}