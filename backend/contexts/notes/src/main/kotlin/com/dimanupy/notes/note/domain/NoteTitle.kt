package com.dimanupy.notes.note.domain

import com.dimanupy.notes.shared.domain.ValueObject

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
