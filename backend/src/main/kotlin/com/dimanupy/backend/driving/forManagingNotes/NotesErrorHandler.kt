package com.dimanupy.backend.driving.forManagingNotes

import com.dimanupy.backend.graph.NoteError
import com.dimanupy.backend.graph.NotionError
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class NotesErrorHandler {

    @ExceptionHandler(NoteError::class, NotionError::class)
    fun handleDomainError(error: Exception, request: HttpServletRequest): ResponseEntity<ApiErrorResponse> {
        val status = HttpStatus.BAD_REQUEST
        val body = ApiErrorResponse(
            status = status.value(),
            error = status.reasonPhrase,
            message = error.message ?: "Bad request unknown error",
            path = request.requestURI,
        )
        return ResponseEntity(body, status)
    }
}
