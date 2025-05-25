package com.dimanupy.backend.graph

import com.dimanupy.backend.graph.ValueObject

class NoteTitle(private val _value: String) : ValueObject<String>(_value) {
    override fun validate(value: String) {
        ensureIsNotEmpty(value)
    }

    private fun ensureIsNotEmpty(value: String) {
        if (value.isEmpty() || value.isBlank()) {
            throw NoteTitleCannotBeEmpty()
        }
    }

    override fun toString(): String = "Title('$_value')"
}
