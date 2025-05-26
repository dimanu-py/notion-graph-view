package com.dimanupy.backend.graph.note

import com.dimanupy.backend.graph.RandomGenerator

object NotionNoteIdPrimitivesMother {

    fun create(value: String = RandomGenerator.uuid()): String = value
}
