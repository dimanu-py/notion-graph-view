package com.dimanupy.notes.note.domain

interface NotionRepository {
    fun sync(databaseId: String): List<String>

}
