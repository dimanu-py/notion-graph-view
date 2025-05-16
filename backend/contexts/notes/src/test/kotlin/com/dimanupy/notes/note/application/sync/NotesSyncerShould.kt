package com.dimanupy.notes.note.application.sync

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
        val notes = listOf(
            Note.fromPrimitives(NoteMother.create()),
            Note.fromPrimitives(NoteMother.create()),
        )
        every { notionRepository.fetch(databaseId) } returns notes

        notesSyncer(databaseId.value)

        verify { notionRepository.fetch(databaseId) }
        verify { notesRepository.save(notes) }
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

    @Test
    fun `not sync notes if notion note id has not valid length`() {
        val idWithWrongLength = "2ca06a5b28d048859c299c02d"
        val invalidNotePrimitives = NoteMother.create(notionId = idWithWrongLength)

        val error = assertThrows<InvalidNotionNoteIdFormat> {
            Note.fromPrimitives(invalidNotePrimitives)
        }

        assertEquals("Notion note id must have a valid UUID format: $idWithWrongLength", error.message)
    }
}