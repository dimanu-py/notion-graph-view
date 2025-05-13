package com.dimanupy.notes.note.domain

data class Note private constructor(
    private val notionNoteId: NotionNoteId,
    private val title: NoteTitle,
    private val url: NoteUrl,
) {
    companion object {
        fun fromPrimitives(notionId: String, title: String, url: String): Note = Note(
            notionNoteId = NotionNoteId(notionId),
            title = NoteTitle(title),
            url = NoteUrl(url),
        )
    }

    fun toPrimitives(): Map<String, String> = mapOf(
        "title" to title.value,
        "url" to url.value,
    )
}
