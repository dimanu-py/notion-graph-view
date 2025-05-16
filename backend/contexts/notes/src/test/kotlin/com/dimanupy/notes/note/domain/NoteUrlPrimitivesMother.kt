package com.dimanupy.notes.note.domain

import com.dimanupy.notes.shared.domain.RandomGenerator

object NoteUrlPrimitivesMother {

    fun create(value: String = RandomGenerator.notionUrl()): String = value

    fun empty(): String = ""
}
