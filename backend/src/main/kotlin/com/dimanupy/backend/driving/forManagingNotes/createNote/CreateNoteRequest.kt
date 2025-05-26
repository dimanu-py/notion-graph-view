package com.dimanupy.backend.driving.forManagingNotes.createNote

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateNoteRequest(
    val id: String,
    val title: String,
    val url: String,
    @JsonProperty("related_notes") val relatedNotes: List<String>
)