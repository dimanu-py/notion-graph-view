package com.dimanupy.notes.note.infra

sealed class NotionError(
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)


class InvalidNotionDatabase(private val id: String) : NotionError("Database with id $id is not shared with your integration.")