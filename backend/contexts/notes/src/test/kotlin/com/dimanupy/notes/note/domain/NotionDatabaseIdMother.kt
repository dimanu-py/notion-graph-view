package com.dimanupy.notes.note.domain

import com.dimanupy.notes.shared.domain.RandomGenerator

object NotionDatabaseIdMother {

    fun create(value: String = RandomGenerator.uuid()): NotionDatabaseId = NotionDatabaseId(value)

    fun fromTestEnvironment(): NotionDatabaseId = NotionDatabaseId(System.getenv("TEST_DATABASE_ID"))
}
