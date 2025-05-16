package com.dimanupy.notes.note.application.create

import com.dimanupy.notes.note.domain.Note
import com.dimanupy.notes.note.domain.NoteMother
import com.dimanupy.notes.note.domain.NoteUrlCannotBeEmpty
import com.dimanupy.notes.note.domain.NoteTitleCannotBeEmpty
import com.dimanupy.notes.note.domain.NoteTitlePrimitivesMother
import com.dimanupy.notes.note.domain.NoteUrlPrimitivesMother
import com.dimanupy.notes.note.domain.NotesRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.Test
import java.util.stream.Stream

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

        verify(exactly = 1) { notesRepository.save(match<Note> { it.toPrimitives() == note })}
    }

    @ParameterizedTest
    @MethodSource("invalidEmptyFieldProvider")
    fun `not create a note if field is empty`(fieldName: String, fieldValue: String, expectedError: Class<out Throwable>) {
        val note = when (fieldName) {
            "url" -> NoteMother.create(url = fieldValue)
            "title" -> NoteMother.create(title = fieldValue)
            else -> throw IllegalArgumentException("Unknown field: $fieldName")
        }

        assertThrows<Throwable>(expectedError.name) {
            noteCreator(note)
        }
    }

    companion object {
        @JvmStatic
        fun invalidEmptyFieldProvider(): Stream<Arguments> = Stream.of(
            Arguments.of("url", NoteUrlPrimitivesMother.empty(), NoteUrlCannotBeEmpty::class.java),
            Arguments.of("title", NoteTitlePrimitivesMother.empty(), NoteTitleCannotBeEmpty::class.java)
        )
    }
}
