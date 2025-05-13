package com.dimanupy.notes.note.domain

import com.dimanupy.notes.shared.domain.RandomGenerator

object NoteRelatedNotesPrimitivesMother {

    fun create(amount: Int = 3): List<String> {
        return List(amount) { RandomGenerator.uuid() }
    }
}