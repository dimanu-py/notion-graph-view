package com.dimanupy.notes.note.domain

object NoteMother {

    fun any(): Note {
        return Note(title = NoteTitleMother.create(), url = NoteUrlMother.create())
    }

    fun create(title: String, url: String): Note {
        return Note(title = NoteTitleMother.create(title), url = NoteUrlMother.create(url))
    }
}