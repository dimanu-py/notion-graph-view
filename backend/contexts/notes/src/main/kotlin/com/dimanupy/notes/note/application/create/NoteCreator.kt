package com.dimanupy.notes.note.application.create

import com.dimanupy.notes.note.domain.NotePrimitives
import com.dimanupy.notes.note.domain.NotesRepository

class NoteCreator(private val repository: NotesRepository) {
    operator fun invoke(note: NotePrimitives) {
        throw NotImplementedError()
    }
}