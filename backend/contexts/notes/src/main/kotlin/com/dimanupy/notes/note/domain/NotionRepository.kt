package com.dimanupy.notes.note.domain

interface NotionRepository {
    fun fetch(databaseId: String): List<Note>

}
