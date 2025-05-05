package com.dimanupy.notes.note.infra

import com.dimanupy.notes.note.domain.Note
import com.dimanupy.notes.note.domain.NotesRepository
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class PostgresNotesRepository: NotesRepository {
    override fun save(notes: List<Note>) {
        transaction {
            notes.forEach { note ->
                val notePrimitives = note.toPrimitives()
                NotesModel.insert {
                    it[title] = notePrimitives["title"]!!
                    it[url] = notePrimitives["url"]!!
                }
            }
        }
    }
}