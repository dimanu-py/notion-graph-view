package com.dimanupy.notes.note.domain

import com.dimanupy.notes.shared.domain.ValueObject


class NotionNoteId(private val _value: String): ValueObject<String>(_value) {
    companion object {
        private const val REQUIRED_LENGTH = 32
    }

    override fun validate(value: String) {
        ensureIsNotEmpty(value)
        ensureLengthIsCorrect(value)
    }

    private fun ensureLengthIsCorrect(value: String) {
        if (value.length != REQUIRED_LENGTH) {
            throw InvalidNotionNoteIdFormat(value)
        }
    }

    private fun ensureIsNotEmpty(value: String) {
        if (value.isEmpty() || value.isBlank()) {
            throw NoteIdCannotBeEmpty()
        }
    }

}