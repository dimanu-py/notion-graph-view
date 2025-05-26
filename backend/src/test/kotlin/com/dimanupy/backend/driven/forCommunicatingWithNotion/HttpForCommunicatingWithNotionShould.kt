package com.dimanupy.backend.driven.forCommunicatingWithNotion

import com.dimanupy.backend.graph.notion.InvalidNotionDatabase
import com.dimanupy.backend.graph.note.Note
import com.dimanupy.backend.graph.note.NotePrimitivesMother
import com.dimanupy.backend.graph.notion.NotionDatabaseIdMother
import com.dimanupy.backend.graph.notion.UnexpectedNotionException
import io.mockk.every
import io.mockk.mockk
import org.http4k.client.JavaHttpClient
import org.http4k.core.HttpHandler
import org.http4k.core.Response
import org.http4k.core.Status
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class HttpForCommunicatingWithNotionShould {

    private lateinit var httpNotionRepository: HttpForCommunicatingWithNotion
    private val connectionData = NotionConnectionData(apiKey = System.getenv("NOTION_API_KEY"))

    @BeforeEach
    fun setUp() {
        httpNotionRepository = HttpForCommunicatingWithNotion(client = JavaHttpClient(), connectionData = connectionData)
    }

    @Test
    fun `fetch notes from valid Notion database`() {
        val databaseId = NotionDatabaseIdMother.create(System.getenv("TEST_DATABASE_ID"))
        val notesPrimitives = listOf(
            NotePrimitivesMother.create(
                notionId = "15bf5bab-5d4e-807b-bf58-ca937660b2fb",
                url = "https://www.notion.so/Note-test-integration-2-15bf5bab5d4e807bbf58ca937660b2fb",
                title = "Note test integration 2",
                relatedNotes = listOf("15bf5bab-5d4e-8064-b9ce-dd66d2024fc6"),
            ),
            NotePrimitivesMother.create(
                notionId = "15bf5bab-5d4e-8064-b9ce-dd66d2024fc6",
                url = "https://www.notion.so/Note-test-integration-1-15bf5bab5d4e8064b9cedd66d2024fc6",
                title = "Note test integration 1",
                relatedNotes = listOf("15bf5bab-5d4e-807b-bf58-ca937660b2fb"),
            ),
        )
        val expectedNotes = notesPrimitives.map { Note.Companion.fromPrimitives(it) }

        val notes = httpNotionRepository.getAllNotes(databaseId)

        assertEquals(expectedNotes, notes)
    }

    @Test
    fun `throw error if database has not integration set`() {
        val databaseId = NotionDatabaseIdMother.create()

        val error = assertThrows<InvalidNotionDatabase> {
            httpNotionRepository.getAllNotes(databaseId)
        }

        assertEquals("Database with id ${databaseId.value} is not shared with your integration.", error.message)
    }

    @Test
    fun `throw error if Notion server returns a 500 status code`() {
        val client = mockk<HttpHandler>()
        every { client(any()) } returns Response.Companion(Status.Companion.INTERNAL_SERVER_ERROR)
        val repository = HttpForCommunicatingWithNotion(client = client, connectionData = connectionData)
        val databaseId = NotionDatabaseIdMother.create()

        val error = assertThrows<UnexpectedNotionException> {
            repository.getAllNotes(databaseId)
        }

        assertContains(error.message, "Unexpected error when fetching Notion database")
    }
}