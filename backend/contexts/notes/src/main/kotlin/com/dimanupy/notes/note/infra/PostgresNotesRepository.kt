package com.dimanupy.notes.note.infra

import com.dimanupy.notes.note.domain.Note
import com.dimanupy.notes.note.domain.NotesRepository
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class PostgresNotesRepository : NotesRepository {
    override fun save(notes: List<Note>) {
        transaction {
            NotesModel.batchInsert(notes) { note ->
                NotesModel.fromPrimitives(note.toPrimitives(), this)
            }
        }
    }

    override fun save(note: Note) {
        transaction {
            NotesModel.insert {
                NotesModel.fromPrimitives(note.toPrimitives(), it)
            }
        }
    }

    override fun findAll(): List<Note> = transaction {
        NotesModel.selectAll().map { note ->
            NotesModel.toAggregate(note)
        }
    }
}
