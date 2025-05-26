package com.dimanupy.backend.graph.driven.forCommunicatingWithNotion

import com.dimanupy.backend.graph.Note
import com.dimanupy.backend.graph.NotionDatabaseId

interface ForCommunicatingWithNotion {
    fun getAllNotes(databaseId: NotionDatabaseId): List<Note>
}