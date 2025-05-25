package com.dimanupy.backend.graph.driving.forManagingNotes.create

import com.dimanupy.notes.note.domain.Note
import com.dimanupy.notes.note.domain.NotePrimitives
import com.dimanupy.notes.note.domain.NotesRepository

class NoteCreator(private val repository: NotesRepository) {
    operator fun invoke(noteInfo: NotePrimitives) {
        val note = Note.fromPrimitives(noteInfo)
        repository.save(note)
    }
}