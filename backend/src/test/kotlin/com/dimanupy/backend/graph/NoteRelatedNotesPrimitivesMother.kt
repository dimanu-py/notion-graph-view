package com.dimanupy.backend.graph

object NoteRelatedNotesPrimitivesMother {

    fun create(amount: Int = 3): List<String> = List(amount) { RandomGenerator.uuid() }

    fun empty(): List<String> = emptyList()
}
