package com.dimanupy.backend.graph.driven.forCommunicatingWithNotion

import com.dimanupy.backend.graph.Note
import com.dimanupy.backend.graph.NotionDatabaseId

interface NotionRepository {
    fun fetch(databaseId: NotionDatabaseId): List<Note>
}