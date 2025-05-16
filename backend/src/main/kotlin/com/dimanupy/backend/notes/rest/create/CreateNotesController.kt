package com.dimanupy.backend.notes.rest.create

import com.dimanupy.notes.note.application.create.NoteCreator
import com.dimanupy.notes.note.domain.NotePrimitives
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
        noteCreator(NotePrimitives(
            notionId = request.id,
            title = request.title,
            url = request.url,
            relatedNotes = request.relatedNotes
        ))
        return ResponseEntity.ok().build()
    }

}
