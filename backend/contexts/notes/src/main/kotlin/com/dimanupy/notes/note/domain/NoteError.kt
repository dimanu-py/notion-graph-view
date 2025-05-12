package com.dimanupy.notes.note.domain

sealed class NoteError(
    override val message: String,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)

class NoteUrlInvalidFormat : NoteError("Note URL must fulfill Notion URL format")
class NoteUrlCannotBeEmpty : NoteError("Note URL cannot be empty")
class NoteTitleCannotBeEmpty : NoteError("Note title cannot be empty")
class NoteIdCannotBeEmpty : NoteError("Note id from Notion cannot be empty")
class InvalidNotionNoteIdFormat(val id: String) : NoteError("Notion note id $id is not valid. Must have 32 characters.")
