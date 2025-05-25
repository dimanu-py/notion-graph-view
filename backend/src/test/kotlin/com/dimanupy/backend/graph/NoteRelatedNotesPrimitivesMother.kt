package com.dimanupy.backend.graph

import com.dimanupy.notes.shared.domain.RandomGenerator

object NoteRelatedNotesPrimitivesMother {

    fun create(amount: Int = 3): List<String> = List(amount) { RandomGenerator.uuid() }

    fun empty(): List<String> = emptyList()
}
