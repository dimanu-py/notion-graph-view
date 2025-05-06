package com.dimanupy.notes.note.domain

import com.dimanupy.notes.shared.domain.ValueObject
import java.util.UUID

class NotionDatabaseId(private val _value: String) : ValueObject<String>(_value) {
    override fun validate(value: String) {
        try {
            UUID.fromString(value)
        } catch (error: IllegalArgumentException) {
            throw InvalidDatabaseIdFormat(value)
        }
    }
}
