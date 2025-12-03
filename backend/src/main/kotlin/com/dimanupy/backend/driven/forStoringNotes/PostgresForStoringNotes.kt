package com.dimanupy.backend.driven.forStoringNotes

import com.dimanupy.backend.graph.driven.forStoringNotes.ForStoringNotes
import com.dimanupy.backend.graph.note.Note
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class PostgresForStoringNotes(private val database: Database) : ForStoringNotes {
    override fun save(notes: List<Note>) {
        transaction(database) {
            NotesModel.batchInsert(notes) { note ->
                NotesModel.fromPrimitives(note.toPrimitives(), this)
            }
        }
    }

    override fun save(note: Note) {
        transaction(database) {
            NotesModel.insert {
                NotesModel.fromPrimitives(note.toPrimitives(), it)
            }
        }
    }

    override fun findAll(): List<Note> = transaction(database) {
        NotesModel.selectAll().map { note ->
            NotesModel.toAggregate(note)
        }
    }
}
