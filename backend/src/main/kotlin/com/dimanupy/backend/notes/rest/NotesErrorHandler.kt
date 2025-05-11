package com.dimanupy.backend.notes.rest

import com.dimanupy.notes.note.domain.NoteError
import com.dimanupy.notes.note.domain.NotionError
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class NotesErrorHandler {

    @ExceptionHandler(NoteError::class, NotionError::class)
    fun handleDomainError(error: Exception, request: HttpServletRequest): ResponseEntity<String> =
        ResponseEntity
            .badRequest()
            .body(error.message)
}