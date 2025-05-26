package com.dimanupy.backend.graph.driving.forManagingNotes.sync

import com.dimanupy.backend.graph.NotionDatabaseId
import com.dimanupy.backend.graph.driven.forStoringNotes.NotesRepository
import com.dimanupy.backend.graph.driven.forCommunicatingWithNotion.ForCommunicatingWithNotion

class NotesSyncer(private val forCommunicatingWithNotion: ForCommunicatingWithNotion, private val notesRepository: NotesRepository) {
    operator fun invoke(id: String) {
        val notionNotes = forCommunicatingWithNotion.getAllNotes(databaseId = NotionDatabaseId(id))
        notesRepository.save(notionNotes)
    }
}