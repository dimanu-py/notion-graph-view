package com.dimanupy.backend.graph.note

object NotePrimitivesMother {

    fun create(
        notionId: String = NotionNoteIdPrimitivesMother.create(),
        url: String = NoteUrlPrimitivesMother.create(),
        title: String = NoteTitlePrimitivesMother.create(),
        relatedNotes: List<String> = NoteRelatedNotesPrimitivesMother.create(),
    ): NotePrimitives = NotePrimitives(
        notionId = notionId,
        title = title,
        url = url,
        relatedNotes = relatedNotes,
    )
}
