package com.dimanupy.backend.graph.notion

import com.dimanupy.backend.graph.RandomGenerator

object NotionDatabaseIdMother {

    fun create(value: String = RandomGenerator.uuid()): NotionDatabaseId = NotionDatabaseId(value)
}