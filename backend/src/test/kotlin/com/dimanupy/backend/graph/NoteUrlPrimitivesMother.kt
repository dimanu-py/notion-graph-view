package com.dimanupy.backend.graph

object NoteUrlPrimitivesMother {

    fun create(value: String = RandomGenerator.notionUrl()): String = value

    fun empty(): String = ""
}
