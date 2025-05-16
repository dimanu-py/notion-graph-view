package com.dimanupy.backend.notes.rest.create

import com.dimanupy.notes.note.application.create.NoteCreator
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notes")
class CreateNotesController(private val noteCreator: NoteCreator) {

    @PutMapping
    fun createNote(@RequestBody request: CreateNoteRequest): ResponseEntity<String> {

        return ResponseEntity.ok().build()
    }

}