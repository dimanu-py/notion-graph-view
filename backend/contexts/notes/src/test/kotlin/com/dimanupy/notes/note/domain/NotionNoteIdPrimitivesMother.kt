package com.dimanupy.notes.note.domain

import com.dimanupy.notes.shared.domain.RandomGenerator

object NotionNoteIdPrimitivesMother {

    fun create(value: String = RandomGenerator.uuid()): String {
        val formatedId = value.replace("-", "")
        return formatedId
    }
}