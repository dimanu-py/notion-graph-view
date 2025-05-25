package com.dimanupy.backend.graph.driving.forManagingNotes.sync

import com.dimanupy.backend.graph.NotionDatabaseId
import com.dimanupy.backend.graph.driven.forStoringNotes.NotesRepository
import com.dimanupy.backend.graph.driven.forStoringNotes.NotionRepository

class NotesSyncer(private val notionRepository: NotionRepository, private val notesRepository: NotesRepository) {
    operator fun invoke(id: String) {
        val notionNotes = notionRepository.fetch(databaseId = NotionDatabaseId(id))
        notesRepository.save(notionNotes)
    }
}