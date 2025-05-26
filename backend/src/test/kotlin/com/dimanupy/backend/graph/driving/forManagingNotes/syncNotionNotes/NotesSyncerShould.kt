package com.dimanupy.backend.graph.driving.forManagingNotes.syncNotionNotes

import com.dimanupy.backend.graph.driven.forCommunicatingWithNotion.ForCommunicatingWithNotion
import com.dimanupy.backend.graph.driven.forStoringNotes.ForStoringNotes
import com.dimanupy.backend.graph.note.InvalidNotionNoteIdFormat
import com.dimanupy.backend.graph.note.Note
import com.dimanupy.backend.graph.note.NotePrimitivesMother
import com.dimanupy.backend.graph.notion.InvalidDatabaseIdFormat
import com.dimanupy.backend.graph.notion.NotionDatabaseIdMother
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class NotesSyncerShould {

    private lateinit var notesSyncer: NotesSyncer
    private lateinit var forCommunicatingWithNotion: ForCommunicatingWithNotion
    private lateinit var forStoringNotes: ForStoringNotes

    @BeforeEach
    fun setUp() {
        forCommunicatingWithNotion = mockk<ForCommunicatingWithNotion>()
        forStoringNotes = mockk<ForStoringNotes>(relaxUnitFun = true)
        notesSyncer = NotesSyncer(forCommunicatingWithNotion, forStoringNotes)
    }

    @Test
    fun `sync notes from Notion database`() {
        val databaseId = NotionDatabaseIdMother.create()
        val notes = listOf(
            Note.fromPrimitives(NotePrimitivesMother.create()),
            Note.fromPrimitives(NotePrimitivesMother.create()),
        )
        every { forCommunicatingWithNotion.getAllNotes(databaseId) } returns notes

        notesSyncer(databaseId.value)

        verify { forCommunicatingWithNotion.getAllNotes(databaseId) }
        verify { forStoringNotes.save(notes) }
    }

    @Test
    fun `not sync notes if database id is not uuid`() {
        val databaseId = "invalid-id"

        val error = assertThrows<InvalidDatabaseIdFormat> {
            notesSyncer(databaseId)
        }

        assertEquals("Database id must have a valid UUID format: $databaseId", error.message)
        verify(exactly = 0) { forCommunicatingWithNotion.getAllNotes(any()) }
    }

    @Test
    fun `not sync notes if notion note id has not valid length`() {
        val idWithWrongLength = "2ca06a5b28d048859c299c02d"
        val invalidNotePrimitives = NotePrimitivesMother.create(notionId = idWithWrongLength)

        val error = assertThrows<InvalidNotionNoteIdFormat> {
            Note.fromPrimitives(invalidNotePrimitives)
        }

        assertEquals("Notion note id must have a valid UUID format: $idWithWrongLength", error.message)
    }
}
