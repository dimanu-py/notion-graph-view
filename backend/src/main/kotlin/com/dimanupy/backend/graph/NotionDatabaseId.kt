package com.dimanupy.backend.graph

import com.dimanupy.backend.graph.ValueObject
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
