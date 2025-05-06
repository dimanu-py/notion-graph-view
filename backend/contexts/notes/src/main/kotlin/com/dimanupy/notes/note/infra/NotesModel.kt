package com.dimanupy.notes.note.infra

import com.dimanupy.notes.note.domain.Note
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.BatchInsertStatement

object NotesModel : Table("notes") {
    private val id = integer("id").autoIncrement()
    val title = varchar("title", 255)
    val url = varchar("url", 255)
    override val primaryKey = PrimaryKey(id)

    fun fromPrimitives(data: Map<String, String>, statement: BatchInsertStatement) {
        statement[title] = data["title"]!!
        statement[url] = data["url"]!!
    }

    fun toAggregate(note: ResultRow): Note = Note.fromPrimitives(
        title = note[title],
        url = note[url],
    )
}
