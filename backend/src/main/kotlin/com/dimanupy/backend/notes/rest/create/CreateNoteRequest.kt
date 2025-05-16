package com.dimanupy.backend.notes.rest.create

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateNoteRequest(
    val id: String,
    val title: String,
    val url: String,
    @JsonProperty("related_notes") val relatedNotes: List<String>
)