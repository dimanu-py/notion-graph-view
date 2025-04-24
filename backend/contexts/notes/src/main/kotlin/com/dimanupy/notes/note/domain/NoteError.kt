package com.dimanupy.notes.note.domain


class NoteUrlInvalidFormat : Throwable("The URL is not a valid Notion URL") {

}

class NoteUrlCannotBeEmpty : Throwable("Note URL cannot be empty") {

}