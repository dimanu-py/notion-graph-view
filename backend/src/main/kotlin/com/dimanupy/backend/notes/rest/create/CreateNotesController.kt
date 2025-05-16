package com.dimanupy.backend.notes.rest.create

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notes")
class CreateNotesController {

    @PutMapping
    fun createNote(): ResponseEntity<String> {
        throw NotImplementedError()
    }

}