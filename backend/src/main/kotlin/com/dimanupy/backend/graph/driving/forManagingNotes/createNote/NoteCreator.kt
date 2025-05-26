package com.dimanupy.backend.graph.driving.forManagingNotes.createNote

import com.dimanupy.backend.graph.note.Note
import com.dimanupy.backend.graph.note.NotePrimitives
import com.dimanupy.backend.graph.driven.forStoringNotes.ForStoringNotes

class NoteCreator(private val repository: ForStoringNotes) {
    operator fun invoke(noteInfo: NotePrimitives) {
        val note = Note.fromPrimitives(noteInfo)
        repository.save(note)
    }
}