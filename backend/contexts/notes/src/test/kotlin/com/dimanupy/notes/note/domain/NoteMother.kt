package com.dimanupy.notes.note.domain

object NoteMother {

    fun any(): Note = Note(notionNoteId = NotionNoteIdMother.create(), title = NoteTitleMother.create(), url = NoteUrlMother.create())

    fun create(
        notionId: String = NotionNoteIdMother.create().value,
        url: String = NoteUrlMother.create().value,
        title: String = NoteTitleMother.create().value
    ): Note = Note(
        notionNoteId = NotionNoteIdMother.create(notionId),
        title = NoteTitleMother.create(title),
        url = NoteUrlMother.create(url),
    )
}
