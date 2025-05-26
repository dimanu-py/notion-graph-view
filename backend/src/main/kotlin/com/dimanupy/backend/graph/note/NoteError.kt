package com.dimanupy.backend.graph.note

sealed class NoteError(
    override val message: String,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)

class NoteUrlInvalidFormat : NoteError("Note URL must fulfill Notion URL format")
class NoteUrlCannotBeEmpty : NoteError("Note URL cannot be empty")
class NoteTitleCannotBeEmpty : NoteError("Note title cannot be empty")
class NoteIdCannotBeEmpty : NoteError("Note id from Notion cannot be empty")
class InvalidNotionNoteIdFormat(val id: String) : NoteError("Notion note id must have a valid UUID format: $id")
