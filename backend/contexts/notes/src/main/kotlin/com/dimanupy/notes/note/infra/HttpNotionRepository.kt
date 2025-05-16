package com.dimanupy.notes.note.infra

import com.dimanupy.notes.note.domain.InvalidNotionDatabase
import com.dimanupy.notes.note.domain.Note
import com.dimanupy.notes.note.domain.NotePrimitives
import com.dimanupy.notes.note.domain.NotionDatabaseId
import com.dimanupy.notes.note.domain.NotionRepository
import com.dimanupy.notes.note.domain.UnexpectedNotionException
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.json.JSONObject

class HttpNotionRepository(private val client: HttpHandler, private val connectionData: NotionConnectionData) : NotionRepository {
    override fun fetch(databaseId: NotionDatabaseId): List<Note> {
        // TODO: Manage when response has pagination when there are more than 100 notes. In cases where the number of notes is greater
        //  than 100, the response will contain a "has_more" field and a "next_cursor" field. The next_cursor field should be used
        //  to fetch the next page of results. The has_more field indicates whether there are more pages of results to fetch.
        val request = buildRequestFor(databaseId)

        val rawResponse = client(request)

        when (rawResponse.status.code) {
            200 -> return parseResponse(rawResponse)
            404 -> throw InvalidNotionDatabase(databaseId.value)
            else -> throw UnexpectedNotionException(
                statusCode = rawResponse.status.code,
                body = rawResponse.bodyString(),
            )
        }
    }

    private fun buildRequestFor(databaseId: NotionDatabaseId) = Request(Method.POST, "https://api.notion.com/v1/databases/${databaseId.value}/query")
        .header("Authorization", "Bearer ${connectionData.apiKey}")
        .header("Notion-Version", "2022-06-28")

    private fun parseResponse(content: Response): List<Note> {
        // TODO: Extract this responsibility to a JSON parser class
        val results = JSONObject(content.bodyString()).getJSONArray("results")
        val notes = mutableListOf<Note>()

        for (i in 0 until results.length()) {
            val note = results.getJSONObject(i)

            val id = note.getString("id")
            val url = note.getString("url")

            val properties = note.getJSONObject("properties")
            val title = properties.getJSONObject("Name").getJSONArray("title").getJSONObject(0).getString("plain_text")
            val rawRelatedNotes = properties.getJSONObject("Related Notes").getJSONArray("relation")
            val relatedNotes = (0 until rawRelatedNotes.length())
                .map { rawRelatedNotes.getJSONObject(it).getString("id") }

            notes.add(Note.fromPrimitives(NotePrimitives(id, title, url, relatedNotes)))
        }
        return notes
    }
}
