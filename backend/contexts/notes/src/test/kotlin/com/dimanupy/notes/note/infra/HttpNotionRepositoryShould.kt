package com.dimanupy.notes.note.infra

import com.dimanupy.notes.note.domain.NoteMother
import org.http4k.client.JavaHttpClient
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals

class HttpNotionRepositoryShould {

    private lateinit var httpNotionRepository: HttpNotionRepository

    @BeforeEach
    fun setUp() {
        httpNotionRepository = HttpNotionRepository(client = JavaHttpClient())
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
}