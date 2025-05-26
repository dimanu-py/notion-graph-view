package com.dimanupy.backend.config

import com.dimanupy.backend.graph.driving.forManagingNotes.create.NoteCreator
import com.dimanupy.backend.graph.driving.forManagingNotes.sync.NotesSyncer
import com.dimanupy.backend.graph.driven.forStoringNotes.NotesRepository
import com.dimanupy.backend.graph.driven.forCommunicatingWithNotion.ForCommunicatingWithNotion
import com.dimanupy.backend.driven.forStoringNotes.PostgresNotesRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyInjectorConfig {
    @Bean
    fun notesRepository() = PostgresNotesRepository()

    @Bean
    fun notesSyncer(forCommunicatingWithNotion: ForCommunicatingWithNotion, notesRepository: NotesRepository): NotesSyncer = NotesSyncer(forCommunicatingWithNotion, notesRepository)

    @Bean
    fun notesCreator(notesRepository: NotesRepository): NoteCreator = NoteCreator(notesRepository)
}
