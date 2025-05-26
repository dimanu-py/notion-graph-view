package com.dimanupy.backend.driving.forManagingNotes.syncNotionNotes

import com.dimanupy.backend.graph.driving.forManagingNotes.syncNotionNotes.NotesSyncer
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