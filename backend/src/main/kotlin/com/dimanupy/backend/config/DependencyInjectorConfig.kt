package com.dimanupy.backend.config

import com.dimanupy.notes.note.application.NotesSyncer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyInjectorConfig {
    @Bean
    fun notesSyncer(): NotesSyncer = NotesSyncer(notionRepository, notesRepository)
}
