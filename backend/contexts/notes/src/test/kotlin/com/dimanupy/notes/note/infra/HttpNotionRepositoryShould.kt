package com.dimanupy.notes.note.infra

import com.dimanupy.notes.note.domain.InvalidNotionDatabase
import com.dimanupy.notes.note.domain.NoteMother
import com.dimanupy.notes.note.domain.NotionDatabaseIdMother
import com.dimanupy.notes.note.domain.UnexpectedNotionException
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

class HttpNotionRepositoryShould {

    private lateinit var httpNotionRepository: HttpNotionRepository
    private val connectionData = NotionConnectionData(apiKey = System.getenv("NOTION_API_KEY"))

    @BeforeEach
    fun setUp() {
        httpNotionRepository = HttpNotionRepository(client = JavaHttpClient(), connectionData = connectionData)
    }

    @Test
    fun `fetch notes from valid Notion database`() {
        val databaseId = NotionDatabaseIdMother.fromTestEnvironment()
        val expectedNotes = listOf(
            NoteMother.create(
                url = "https://www.notion.so/Note-test-integration-2-15bf5bab5d4e807bbf58ca937660b2fb",
                title = "Note test integration 2",
            ),
            NoteMother.create(
                url = "https://www.notion.so/Note-test-integration-1-15bf5bab5d4e8064b9cedd66d2024fc6",
                title = "Note test integration 1",
            )
        )

        val notes = httpNotionRepository.fetch(databaseId)

        assertEquals(expectedNotes, notes)
    }

    @Test
    fun `throw error if database has not integration set`() {
        val databaseId = NotionDatabaseIdMother.create()

        val error = assertThrows<InvalidNotionDatabase> {
            httpNotionRepository.fetch(databaseId)
        }

        assertEquals("Database with id ${databaseId.value} is not shared with your integration.", error.message)
    }

    @Test
    fun `throw error if Notion server returns a 500 status code`() {
        val client = mockk<HttpHandler>()
        every { client(any()) } returns Response(Status.INTERNAL_SERVER_ERROR)
        val repository = HttpNotionRepository(client = client, connectionData = connectionData)
        val databaseId = NotionDatabaseIdMother.create()

        val error = assertThrows<UnexpectedNotionException> {
            repository.fetch(databaseId)
        }

        assertContains(error.message, "Unexpected error when fetching Notion database")
    }
}