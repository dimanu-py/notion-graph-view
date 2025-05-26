package com.dimanupy.backend.graph.note

import com.dimanupy.backend.graph.ValueObject

class NoteRelatedNotes private constructor(private val _value: List<NoteNotionId>) : ValueObject<List<NoteNotionId>>(_value) {
    companion object {
        fun create(notesIds: List<String>): NoteRelatedNotes = NoteRelatedNotes(
            notesIds.map { NoteNotionId(it) },
        )
    }

    override fun validate(value: List<NoteNotionId>) {
        // No extra validation needed
    }

    override fun toString(): String = "RelatedNotes(${_value.joinToString(", ")})"
}
