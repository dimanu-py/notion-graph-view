package com.dimanupy.notes.note.domain

data class Note(
    val title: NoteTitle,
    val url: NoteUrl
) {
    companion object {
        fun fromPrimitives(title: String, url: String): Note {
            return Note(
                title = NoteTitle(title),
                url = NoteUrl(url)
            )
        }
    }

    fun toPrimitives(): Map<String, String> = mapOf(
        "title" to title.value,
        "url" to url.value
    )
}