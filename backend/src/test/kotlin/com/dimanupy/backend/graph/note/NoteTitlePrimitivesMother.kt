package com.dimanupy.backend.graph.note

import com.dimanupy.backend.graph.RandomGenerator

object NoteTitlePrimitivesMother {

    fun create(value: String = RandomGenerator.title()): String = value

    fun empty(): String = ""
}
