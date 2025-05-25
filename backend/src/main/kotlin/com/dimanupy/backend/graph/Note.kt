package com.dimanupy.backend.graph

data class NotePrimitives(
    val notionId: String,
    val title: String,
    val url: String,
    val relatedNotes: List<String>
)

data class Note private constructor(
    private val notionNoteId: NotionNoteId,
    private val title: NoteTitle,
    private val url: NoteUrl,
    private val relatedNotes: NoteRelatedNotes,
) {
    companion object {
        fun fromPrimitives(primitives: NotePrimitives): Note = Note(
            notionNoteId = NotionNoteId(primitives.notionId),
            title = NoteTitle(primitives.title),
            url = NoteUrl(primitives.url),
            relatedNotes = NoteRelatedNotes.create(primitives.relatedNotes),
        )
    }

    fun toPrimitives(): NotePrimitives = NotePrimitives(
        notionId = notionNoteId.value,
        title = title.value,
        url = url.value,
        relatedNotes = relatedNotes.value.map { it.value }
    )
}
