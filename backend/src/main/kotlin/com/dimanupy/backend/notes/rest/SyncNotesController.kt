package com.dimanupy.backend.notes.rest

import com.dimanupy.notes.note.application.NotesSyncer
import com.dimanupy.notes.note.domain.InvalidDatabaseIdFormat
import com.dimanupy.notes.note.domain.InvalidNotionDatabase
import com.dimanupy.notes.note.domain.NotionError
import com.dimanupy.notes.note.domain.UnexpectedNotionException
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
        return try {
            notesSyncer(databaseId)
            ResponseEntity.ok().build()
        } catch (error: NotionError) {
            when (error) {
                is InvalidNotionDatabase,
                is InvalidDatabaseIdFormat,
                is UnexpectedNotionException -> ResponseEntity.badRequest().build()
                else -> ResponseEntity.internalServerError().build()
            }
        }
    }
}
