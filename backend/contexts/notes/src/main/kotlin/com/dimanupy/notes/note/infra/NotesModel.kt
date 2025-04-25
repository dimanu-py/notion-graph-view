package com.dimanupy.notes.note.infra

import org.jetbrains.exposed.sql.Table

object NotesModel : Table("notes") {
    private val id = integer("id").autoIncrement()

    override val primaryKey = PrimaryKey(id)
}