package com.dimanupy.backend.graph

object NoteTitlePrimitivesMother {

    fun create(value: String = RandomGenerator.title()): String = value

    fun empty(): String = ""
}
