package com.dimanupy.backend.driving.forManagingNotes

import java.time.Instant

data class ApiErrorResponse(
    val occurredOn: Instant = Instant.now(),
    val status: Int,
    val error: String,
    val message: String,
    val path: String,
)
