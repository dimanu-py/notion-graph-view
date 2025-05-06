package com.dimanupy.notes.note.domain

object NoteMother {

    fun any(): Note = Note(title = NoteTitleMother.create(), url = NoteUrlMother.create())

    fun create(url: String, title: String): Note = Note(
        title = NoteTitleMother.create(title),
        url = NoteUrlMother.create(url),
    )
}
