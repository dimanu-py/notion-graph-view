package com.dimanupy.backend.graph.driven.forStoringNotes

interface NotionRepository {
    fun fetch(databaseId: NotionDatabaseId): List<Note>
}
