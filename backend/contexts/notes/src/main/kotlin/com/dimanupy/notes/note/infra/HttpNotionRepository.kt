package com.dimanupy.notes.note.infra

import com.dimanupy.notes.note.domain.Note
import com.dimanupy.notes.note.domain.NotionRepository
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.json.JSONObject

class HttpNotionRepository(private val client: HttpHandler) : NotionRepository {
    override fun fetch(databaseId: String): List<Note> {
        val request = buildRequestFor(databaseId)

        val rawResponse = client(request)

        return parseResponse(rawResponse)
    }

    private fun buildRequestFor(databaseId: String) = Request(Method.POST, "https://api.notion.com/v1/databases/$databaseId/query")
        .header("Authorization", "Bearer ${System.getenv("NOTION_API_KEY")}")
        .header("Notion-Version", "2022-06-28")

    private fun parseResponse(content: Response): List<Note> {
        val results = JSONObject(content.bodyString()).getJSONArray("results")
        val notes = mutableListOf<Note>()

        for (i in 0 until results.length()) {
            val note = results.getJSONObject(i)

            val url = note.getString("url")

            val properties = note.getJSONObject("properties")
            val title = properties.getJSONObject("Name").getJSONArray("title").getJSONObject(0).getString("plain_text")

            notes.add(Note.fromPrimitives(title, url))
        }
        return notes

    }
}