package com.dimanupy.notes.note.domain

data class Note(
    private val title: NoteTitle,
    private val url: NoteUrl,
) {
    companion object {
        fun fromPrimitives(title: String, url: String): Note = Note(
            title = NoteTitle(title),
            url = NoteUrl(url),
        )
    }

    fun toPrimitives(): Map<String, String> = mapOf(
        "title" to title.value,
        "url" to url.value,
    )
}
