package com.dimanupy.notes.note.infra

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.BatchInsertStatement

object NotesModel : Table("notes") {
    val title = varchar("title", 255)
    val url = varchar("url", 255)

    fun fromPrimitives(data: Map<String, String>, statement: BatchInsertStatement) {
        statement[title] = data["title"]!!
        statement[url] = data["url"]!!
    }
}