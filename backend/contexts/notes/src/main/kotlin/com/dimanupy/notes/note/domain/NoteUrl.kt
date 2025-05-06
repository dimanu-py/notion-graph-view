package com.dimanupy.notes.note.domain

import com.dimanupy.notes.shared.domain.ValueObject

class NoteUrl(private val _value: String) : ValueObject<String>(_value) {
    companion object {
        private const val VALID_URL_PATTERN = "^(https?://)?(www\\.)?notion\\.so/.*"
        private val HTTPS_NOTION_URL_REGEX = Regex(VALID_URL_PATTERN)
    }

    override fun validate(value: String) {
        ensureIsNotEmpty(value)
        ensureUrlIsFromNotion(value)
    }

    private fun ensureUrlIsFromNotion(value: String) {
        if (!value.matches(HTTPS_NOTION_URL_REGEX)) {
            throw NoteUrlInvalidFormat()
        }
    }

    private fun ensureIsNotEmpty(value: String) {
        if (value.isEmpty() || value.isBlank()) {
            throw NoteUrlCannotBeEmpty()
        }
    }

    override fun toString(): String = "Url('$_value')"
}
