package com.dimanupy.notes.note.domain

object NoteMother {

    fun create(title: String, url: String): Note {
        return Note(title = title, url = url)
    }
}