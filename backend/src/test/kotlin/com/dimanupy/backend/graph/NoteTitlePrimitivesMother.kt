package com.dimanupy.backend.graph

import com.dimanupy.notes.shared.domain.RandomGenerator

object NoteTitlePrimitivesMother {

    fun create(value: String = RandomGenerator.title()): String = value

    fun empty(): String = ""
}
