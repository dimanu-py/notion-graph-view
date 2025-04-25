package com.dimanupy.notes.note.infra

import com.dimanupy.notes.note.domain.NoteMother
import org.http4k.client.JavaHttpClient
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
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
        val databaseId = System.getenv("TEST_DATABASE_ID")
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
        val databaseId = "e7141ea7-a862-4f70-bb6a-b7ce0fe0eb3f"

        val error = assertThrows<InvalidNotionDatabase> {
            httpNotionRepository.fetch(databaseId)
        }

        assertEquals("Database with id $databaseId is not shared with your integration.", error.message)
    }
}