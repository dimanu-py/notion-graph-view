package com.dimanupy.notes.note.domain

import com.dimanupy.notes.shared.domain.RandomGenerator

object NotionDatabaseIdMother {

    fun create(value: String = RandomGenerator.uuid()): NotionDatabaseId {
        return NotionDatabaseId(value)
    }

}