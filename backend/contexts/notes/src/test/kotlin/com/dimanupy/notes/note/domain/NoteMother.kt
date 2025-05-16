package com.dimanupy.notes.note.domain

object NoteMother {

    fun anyPrimitives(): NotePrimitives = NotePrimitives(
        notionId = NotionNoteIdPrimitivesMother.create(),
        title = NoteTitlePrimitivesMother.create(),
        url = NoteUrlPrimitivesMother.create(),
        relatedNotes = NoteRelatedNotesPrimitivesMother.create(),
    )

    fun create(
        notionId: String = NotionNoteIdPrimitivesMother.create(),
        url: String = NoteUrlPrimitivesMother.create(),
        title: String = NoteTitlePrimitivesMother.create(),
        relatedNotes: List<String> = NoteRelatedNotesPrimitivesMother.create(),
    ): Note = Note.fromPrimitives(
        NotePrimitives(
        notionId = notionId,
        title = title,
        url = url,
        relatedNotes = relatedNotes,
        )
    )
}
