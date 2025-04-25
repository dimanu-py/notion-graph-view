package com.dimanupy.notes.note.infra

import com.dimanupy.notes.note.domain.Note
import com.dimanupy.notes.note.domain.NotesRepository

class PostgresNotesRepository: NotesRepository {
    override fun save(notes: List<Note>) {
        throw NotImplementedError()
    }
}