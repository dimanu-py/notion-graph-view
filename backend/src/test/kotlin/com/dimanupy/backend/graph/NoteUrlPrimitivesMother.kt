package com.dimanupy.backend.graph

import com.dimanupy.notes.shared.domain.RandomGenerator

object NoteUrlPrimitivesMother {

    fun create(value: String = RandomGenerator.notionUrl()): String = value

    fun empty(): String = ""
}
