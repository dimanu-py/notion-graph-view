package com.dimanupy.notes.note.domain

interface NotesRepository {
    fun save(notes: List<Note>)

}
