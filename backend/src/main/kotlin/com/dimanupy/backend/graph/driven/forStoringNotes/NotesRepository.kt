package com.dimanupy.backend.graph.driven.forStoringNotes

interface NotesRepository {
    fun save(notes: List<Note>)

    fun save(note: Note)

    fun findAll(): List<Note>
}
