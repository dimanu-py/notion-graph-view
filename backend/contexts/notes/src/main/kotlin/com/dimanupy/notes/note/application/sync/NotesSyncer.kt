package com.dimanupy.notes.note.application.sync

import com.dimanupy.notes.note.domain.NotesRepository
import com.dimanupy.notes.note.domain.NotionDatabaseId
import com.dimanupy.notes.note.domain.NotionRepository

class NotesSyncer(private val notionRepository: NotionRepository, private val notesRepository: NotesRepository) {
    operator fun invoke(id: String) {
        val notionNotes = notionRepository.fetch(databaseId = NotionDatabaseId(id))
        notesRepository.save(notionNotes)
    }
}