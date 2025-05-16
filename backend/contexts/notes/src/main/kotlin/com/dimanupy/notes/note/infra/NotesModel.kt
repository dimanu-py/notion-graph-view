package com.dimanupy.notes.note.infra

import com.dimanupy.notes.note.domain.Note
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.json.jsonb
import org.jetbrains.exposed.sql.statements.BatchInsertStatement

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

    fun fromPrimitives(data: Map<String, Any>, statement: BatchInsertStatement) {
        statement[notionId] = data["notionId"] as String
        statement[title] = data["title"] as String
        statement[url] = data["url"] as String
        statement[relatedNotes] = data.getValue("relatedNotes") as List<String>
    }

    fun toAggregate(note: ResultRow): Note = Note.fromPrimitives(
        notionId = note[notionId],
        title = note[title],
        url = note[url],
        relatedNotes = note[relatedNotes],
    )
}
