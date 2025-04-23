package com.dimanupy.notes.note.domain

object NoteTitleMother {

    fun create(name: String): NoteTitle {
        return NoteTitle(name)
    }
}