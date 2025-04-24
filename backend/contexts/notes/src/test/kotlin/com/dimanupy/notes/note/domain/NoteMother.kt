package com.dimanupy.notes.note.domain

object NoteMother {

    fun any(): Note {
        return Note(title = NoteTitleMother.create(), url = NoteUrlMother.create())
    }

}