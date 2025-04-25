package com.dimanupy.notes.note.application

import com.dimanupy.notes.note.domain.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class NotesSyncerShould {

    private lateinit var notesSyncer: NotesSyncer
    private lateinit var notionRepository: NotionRepository
    private lateinit var notesRepository: NotesRepository

    @BeforeEach
    fun setUp() {
        notionRepository = mockk<NotionRepository>()
        notesRepository = mockk<NotesRepository>(relaxUnitFun = true)
        notesSyncer = NotesSyncer(notionRepository, notesRepository)
    }

    @Test
    fun `sync notes from Notion database`() {
        val databaseId = NotionDatabaseIdMother.create()
        val notes = listOf(NoteMother.any(), NoteMother.any())
        every { notionRepository.fetch(databaseId) } returns notes

        notesSyncer(databaseId.value)

        verify { notionRepository.fetch(databaseId) }
        verify { notesRepository.save(notes)}
    }

    @Test
    fun `not sync notes if database id is not uuid`() {
        val databaseId = "invalid-id"

        val error = assertThrows<InvalidDatabaseIdFormat> {
            notesSyncer(databaseId)
        }

        assertEquals("Database id must have a valid UUID format: $databaseId", error.message)
        verify(exactly = 0) { notionRepository.fetch(any()) }
    }
}