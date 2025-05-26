package com.dimanupy.backend.graph.driving.forManagingNotes.createNote

import com.dimanupy.backend.graph.*
import com.dimanupy.backend.graph.driven.forStoringNotes.ForStoringNotes
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.Test

class NoteCreatorShould {
    private lateinit var noteCreator: NoteCreator
    private lateinit var forStoringNotes: ForStoringNotes

    @BeforeEach
    fun setUp() {
        forStoringNotes = mockk<ForStoringNotes>(relaxUnitFun = true)
        noteCreator = NoteCreator(forStoringNotes)
    }

    @Test
    fun `create a valid note`() {
        val note = NoteMother.create()

        noteCreator(note)

        verify(exactly = 1) { forStoringNotes.save(match<Note> { it.toPrimitives() == note })}
    }

    @Test
    fun `create a note with no relations`() {
        val note = NoteMother.create(relatedNotes = NoteRelatedNotesPrimitivesMother.empty())

        noteCreator(note)

        verify(exactly = 1) { forStoringNotes.save(match<Note> { it.toPrimitives() == note }) }
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
