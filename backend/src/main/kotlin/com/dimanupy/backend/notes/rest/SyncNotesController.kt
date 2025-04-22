package com.dimanupy.backend.notes.rest

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notes")
class SyncNotesController {

    @PutMapping("/sync/{databaseId}")
    fun syncNotes(@PathVariable databaseId: String) {
        throw NotImplementedError()
    }
}