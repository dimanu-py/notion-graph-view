package com.dimanupy.notes.note.domain

data class Note private constructor(
    private val notionNoteId: NotionNoteId,
    private val title: NoteTitle,
    private val url: NoteUrl,
    private val relatedNotes: NoteRelatedNotes,
) {
    companion object {
        fun fromPrimitives(notionId: String, title: String, url: String, relatedNotes: List<String> = emptyList()): Note = Note(
            notionNoteId = NotionNoteId(notionId),
            title = NoteTitle(title),
            url = NoteUrl(url),
            relatedNotes = NoteRelatedNotes.create(relatedNotes),
        )
    }

    fun toPrimitives(): Map<String, Any> = mapOf(
        "notionId" to notionNoteId.value,
        "title" to title.value,
        "url" to url.value,
        "relatedNotes" to relatedNotes.value.map { it.value },
    )
}
