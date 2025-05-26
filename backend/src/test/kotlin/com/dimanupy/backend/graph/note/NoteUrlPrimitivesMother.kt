package com.dimanupy.backend.graph.note

import com.dimanupy.backend.graph.RandomGenerator

object NoteUrlPrimitivesMother {

    fun create(value: String = RandomGenerator.notionUrl()): String = value

    fun empty(): String = ""
}
