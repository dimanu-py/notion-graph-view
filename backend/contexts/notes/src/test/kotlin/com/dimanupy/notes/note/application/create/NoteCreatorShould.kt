package com.dimanupy.notes.note.application.create

import com.dimanupy.notes.note.domain.Note
import com.dimanupy.notes.note.domain.NoteMother
import com.dimanupy.notes.note.domain.NotesRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class NoteCreatorShould {
    private lateinit var noteCreator: NoteCreator
    private lateinit var notesRepository: NotesRepository

    @BeforeEach
    fun setUp() {
        notesRepository = mockk<NotesRepository>(relaxUnitFun = true)
        noteCreator = NoteCreator(notesRepository)
    }

    @Test
    fun `create a valid note`() {
        val note = NoteMother.create()

        noteCreator(note)

        val expectedSavedNote = Note.fromPrimitives(note)
        verify { notesRepository.save(expectedSavedNote) }
    }
}