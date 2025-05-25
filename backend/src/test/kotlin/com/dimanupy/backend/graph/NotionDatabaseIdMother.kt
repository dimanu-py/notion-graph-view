package com.dimanupy.backend.graph

import com.dimanupy.notes.shared.domain.RandomGenerator

object NotionDatabaseIdMother {

    fun create(value: String = RandomGenerator.uuid()): NotionDatabaseId = NotionDatabaseId(value)
}
