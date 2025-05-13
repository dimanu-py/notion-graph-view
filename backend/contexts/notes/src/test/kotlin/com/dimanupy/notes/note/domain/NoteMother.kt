package com.dimanupy.notes.note.domain

object NoteMother {

    fun any(): Note = Note.fromPrimitives(notionId = NotionNoteIdPrimitivesMother.create(), title = NoteTitlePrimitivesMother.create(), url = NoteUrlPrimitivesMother.create())

    fun create(
        notionId: String = NotionNoteIdPrimitivesMother.create(),
        url: String = NoteUrlPrimitivesMother.create(),
        title: String = NoteTitlePrimitivesMother.create(),
    ): Note = Note.fromPrimitives(
        notionId = notionId,
        title = title,
        url = url,
    )
}
