package com.dimanupy.notes.note.domain

import com.dimanupy.notes.shared.domain.RandomGenerator

object NoteUrlMother {

    fun create(value: String = RandomGenerator.notionUrl()): NoteUrl {
        return NoteUrl(value)
    }
}