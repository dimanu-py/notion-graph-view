package com.dimanupy.notes.note.infra

import org.jetbrains.exposed.sql.Table

object NotesModel : Table("notes") {
    val title = varchar("title", 255)
    val url = varchar("url", 255)
}