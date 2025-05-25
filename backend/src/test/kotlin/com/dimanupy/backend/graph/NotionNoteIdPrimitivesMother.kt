package com.dimanupy.backend.graph

import com.dimanupy.notes.shared.domain.RandomGenerator

object NotionNoteIdPrimitivesMother {

    fun create(value: String = RandomGenerator.uuid()): String = value
}
