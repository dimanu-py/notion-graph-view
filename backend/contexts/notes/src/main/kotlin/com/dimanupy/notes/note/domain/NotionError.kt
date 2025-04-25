package com.dimanupy.notes.note.domain

sealed class NotionError(
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)


class InvalidNotionDatabase(private val id: String) : NotionError("Database with id $id is not shared with your integration.")
class UnexpectedNotionException(
    private val statusCode: Int,
    private val body: String
) : NotionError("Unexpected error when fetching Notion database. Notion server returned $statusCode with body $body")
class InvalidDatabaseIdFormat(private val id: String) : NotionError("Database id must have a valid UUID format: $id")