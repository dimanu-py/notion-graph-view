package com.dimanupy.backend.graph.driven.forStoringNotes

import com.dimanupy.backend.graph.Note

interface ForStoringNotes {
    fun save(notes: List<Note>)

    fun save(note: Note)

    fun findAll(): List<Note>
}
