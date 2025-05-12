package com.dimanupy.notes.note.domain

import com.dimanupy.notes.shared.domain.RandomGenerator

object NotionNoteIdMother {

    fun create(value: String = RandomGenerator.uuid()): NotionNoteId {
        val formatedId = value.replace("-", "")
        return NotionNoteId(formatedId)
    }
}