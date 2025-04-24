package com.dimanupy.backend.config

import com.dimanupy.notes.note.application.NotesSyncer
import com.dimanupy.notes.note.domain.NotionRepository
import com.dimanupy.notes.note.infra.HttpNotionRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyInjectorConfig {
    @Bean
    fun notesSyncer(notionRepository: NotionRepository): NotesSyncer = NotesSyncer(notionRepository, notesRepository)
}
