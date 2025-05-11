package com.dimanupy.backend

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
class UnexpectedErrorHandler {

    @ExceptionHandler(Exception::class)
    fun handleUnexpectedError(error: Exception): ResponseEntity<String> =
        ResponseEntity
            .internalServerError()
            .body("An unexpected error occurred")
}