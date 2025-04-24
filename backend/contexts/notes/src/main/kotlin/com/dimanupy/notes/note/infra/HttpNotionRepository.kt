package com.dimanupy.notes.note.infra

import com.dimanupy.notes.note.domain.Note
import com.dimanupy.notes.note.domain.NotionRepository

class HttpNotionRepository : NotionRepository {
    override fun fetch(databaseId: String): List<Note> {
        throw NotImplementedError()
    }
}