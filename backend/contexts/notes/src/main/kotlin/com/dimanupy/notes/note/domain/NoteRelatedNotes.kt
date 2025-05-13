package com.dimanupy.notes.note.domain

import com.dimanupy.notes.shared.domain.ValueObject

class NoteRelatedNotes private constructor(private val _value: List<NotionNoteId>): ValueObject<List<NotionNoteId>>(_value) {
    companion object {
        fun create(notesIds: List<String>): NoteRelatedNotes {
            return NoteRelatedNotes(
                notesIds.map { NotionNoteId(it) }
            )
        }
    }

    override fun validate(value: List<NotionNoteId>) {
        // No extra validation needed
    }

    override fun toString(): String {
        return "RelatedNotes(${_value.joinToString(", ")})"
    }
}