package com.dimanupy.backend.config

import com.dimanupy.notes.note.application.create.NoteCreator
import com.dimanupy.notes.note.application.sync.NotesSyncer
import com.dimanupy.backend.graph.driven.forStoringNotes.NotesRepository
import com.dimanupy.backend.graph.driven.forStoringNotes.NotionRepository
import com.dimanupy.backend.driven.forStoringNotes.PostgresNotesRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyInjectorConfig {
    @Bean
    fun notesRepository() = PostgresNotesRepository()

    @Bean
    fun notesSyncer(notionRepository: NotionRepository, notesRepository: NotesRepository): NotesSyncer = NotesSyncer(notionRepository, notesRepository)

    @Bean
    fun notesCreator(notesRepository: NotesRepository): NoteCreator = NoteCreator(notesRepository)
}
