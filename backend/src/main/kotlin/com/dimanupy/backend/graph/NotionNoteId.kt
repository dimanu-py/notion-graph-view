package com.dimanupy.backend.graph

import com.dimanupy.backend.graph.ValueObject
import java.util.UUID

class NotionNoteId(private val _value: String) : ValueObject<String>(_value) {

    override fun validate(value: String) {
        ensureIsNotEmpty(value)
        ensureFormatIsCorrect(value)
    }

    private fun ensureFormatIsCorrect(value: String) {
        try {
            UUID.fromString(value)
        } catch (error: IllegalArgumentException) {
            throw InvalidNotionNoteIdFormat(value)
        }
    }

    private fun ensureIsNotEmpty(value: String) {
        if (value.isEmpty() || value.isBlank()) {
            throw NoteIdCannotBeEmpty()
        }
    }
}
