package com.dimanupy.backend

import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
class UnexpectedErrorHandler {

    @ExceptionHandler(Exception::class)
    fun handleUnexpectedError(error: Exception, request: HttpServletRequest): ResponseEntity<ApiErrorResponse> {
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        val body = ApiErrorResponse(
            status = status.value(),
            error = status.reasonPhrase,
            message = "An unexpected error occurred.",
            path = request.requestURI,
        )
        return ResponseEntity(body, status)
    }
}
