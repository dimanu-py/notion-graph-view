package com.dimanupy.notes.note.application

import com.dimanupy.notes.note.domain.NoteMother
import com.dimanupy.notes.note.domain.NotesRepository
import com.dimanupy.notes.note.domain.NotionRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

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
        val databaseId = "valid-database-id"
        val notes = listOf(
            NoteMother.create("Note 1", "https://notion.example.com/notes/1"),
            NoteMother.create("Note 2", "https://notion.example.com/notes/2"),
        )
        every { notionRepository.sync(databaseId) } returns notes

        notesSyncer(databaseId)

        verify { notionRepository.sync(databaseId) }
        verify { notesRepository.save(notes)}
    }
}