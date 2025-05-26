package com.dimanupy.backend.graph.driven.forCommunicatingWithNotion

import com.dimanupy.backend.graph.note.Note
import com.dimanupy.backend.graph.notion.NotionDatabaseId

interface ForCommunicatingWithNotion {
    fun getAllNotes(databaseId: NotionDatabaseId): List<Note>
}
