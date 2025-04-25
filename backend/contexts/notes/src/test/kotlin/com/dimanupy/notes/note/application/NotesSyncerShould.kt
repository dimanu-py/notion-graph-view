package com.dimanupy.notes.note.application

import com.dimanupy.notes.note.domain.NoteMother
import com.dimanupy.notes.note.domain.NotesRepository
import com.dimanupy.notes.note.domain.NotionDatabaseIdMother
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
        val databaseId = NotionDatabaseIdMother.create()
        val notes = listOf(NoteMother.any(), NoteMother.any())
        every { notionRepository.fetch(databaseId) } returns notes

        notesSyncer(databaseId.value)

        verify { notionRepository.fetch(databaseId) }
        verify { notesRepository.save(notes)}
    }
}