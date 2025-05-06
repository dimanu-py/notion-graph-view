package com.dimanupy.notes.note.domain

import com.dimanupy.notes.shared.domain.RandomGenerator

object NoteTitleMother {

    fun create(value: String = RandomGenerator.title()): NoteTitle = NoteTitle(value)
}
