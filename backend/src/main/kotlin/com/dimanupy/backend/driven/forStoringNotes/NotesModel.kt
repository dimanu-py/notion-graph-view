package com.dimanupy.backend.driven.forStoringNotes

import com.dimanupy.backend.graph.note.Note
import com.dimanupy.backend.graph.note.NotePrimitives
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.json.jsonb
import org.jetbrains.exposed.sql.statements.BatchInsertStatement
import org.jetbrains.exposed.sql.statements.InsertStatement

object NotesModel : Table("notes") {
    private val id = integer("id").autoIncrement()
    private val notionId = varchar("notion_id", 255)
    private val title = varchar("title", 255)
    private val url = varchar("url", 255)
    private val relatedNotes = jsonb(
        name = "related_notes",
        jsonConfig = Json { encodeDefaults = true },
        kSerializer = ListSerializer(String.serializer()),
    )
    override val primaryKey = PrimaryKey(id)

    fun fromPrimitives(data: NotePrimitives, statement: BatchInsertStatement) {
        statement[notionId] = data.notionId
        statement[title] = data.title
        statement[url] = data.url
        statement[relatedNotes] = data.relatedNotes
    }

    fun fromPrimitives(data: NotePrimitives, statement: InsertStatement<Number>) {
        statement[notionId] = data.notionId
        statement[title] = data.title
        statement[url] = data.url
        statement[relatedNotes] = data.relatedNotes
    }

    fun toAggregate(note: ResultRow): Note = Note.fromPrimitives(
        NotePrimitives(
            notionId = note[notionId],
            title = note[title],
            url = note[url],
            relatedNotes = note[relatedNotes],
        ),
    )
}
