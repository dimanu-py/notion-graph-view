package com.dimanupy.backend.graph

object NotionDatabaseIdMother {

    fun create(value: String = RandomGenerator.uuid()): NotionDatabaseId = NotionDatabaseId(value)
}
