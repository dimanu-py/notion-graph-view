package com.dimanupy.backend.graph.notion

import com.dimanupy.backend.graph.ValueObject
import java.util.UUID

class NotionDatabaseId(private val _value: String) : ValueObject<String>(_value) {

    val notionFormat: String
        get() = value.replace("-", "")

    override fun validate(value: String) {
        try {
            val normalizedValue = if (value.length == 32 && !value.contains("-")) {
                "${value.substring(0, 8)}-${value.substring(8, 12)}-${value.substring(12, 16)}-${value.substring(16, 20)}-${value.substring(20, 32)}"
            } else {
                value
            }
            UUID.fromString(normalizedValue)
        } catch (_: IllegalArgumentException) {
            throw InvalidDatabaseIdFormat(value)
        }
    }
}
