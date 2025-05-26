package com.dimanupy.backend.graph.driving.forManagingNotes.sync

import com.dimanupy.backend.graph.NotionDatabaseId
import com.dimanupy.backend.graph.driven.forStoringNotes.ForStoringNotes
import com.dimanupy.backend.graph.driven.forCommunicatingWithNotion.ForCommunicatingWithNotion

class NotesSyncer(private val forCommunicatingWithNotion: ForCommunicatingWithNotion, private val forStoringNotes: ForStoringNotes) {
    operator fun invoke(id: String) {
        val notionNotes = forCommunicatingWithNotion.getAllNotes(databaseId = NotionDatabaseId(id))
        forStoringNotes.save(notionNotes)
    }
}