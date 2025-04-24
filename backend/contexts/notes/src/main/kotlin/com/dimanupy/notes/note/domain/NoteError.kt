package com.dimanupy.notes.note.domain

sealed class NoteError(
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)

class NoteUrlInvalidFormat : NoteError("Note URL must fulfill Notion URL format") {

}

class NoteUrlCannotBeEmpty : NoteError("Note URL cannot be empty") {

}

class NoteTitleCannotBeEmpty : NoteError("Note title cannot be empty") {

}