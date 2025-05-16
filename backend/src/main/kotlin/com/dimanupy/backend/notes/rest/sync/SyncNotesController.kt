package com.dimanupy.backend.notes.rest.sync

import com.dimanupy.notes.note.application.sync.NotesSyncer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notes")
class SyncNotesController(private val notesSyncer: NotesSyncer) {

    @PutMapping("/sync/{databaseId}")
    fun syncNotes(@PathVariable databaseId: String): ResponseEntity<String> {
        notesSyncer(databaseId)
        return ResponseEntity.ok().build()
    }
}