package com.dimanupy.backend.config

import com.dimanupy.backend.driven.forStoringNotes.PostgresForStoringNotes
import com.dimanupy.backend.graph.driven.forCommunicatingWithNotion.ForCommunicatingWithNotion
import com.dimanupy.backend.graph.driven.forStoringNotes.ForStoringNotes
import com.dimanupy.backend.graph.driving.forManagingNotes.createNote.NoteCreator
import com.dimanupy.backend.graph.driving.forManagingNotes.syncNotionNotes.NotesSyncer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyInjectorConfig {
    @Bean
    fun notesRepository() = PostgresForStoringNotes()

    @Bean
    fun notesSyncer(forCommunicatingWithNotion: ForCommunicatingWithNotion, forStoringNotes: ForStoringNotes): NotesSyncer = NotesSyncer(forCommunicatingWithNotion, forStoringNotes)

    @Bean
    fun notesCreator(forStoringNotes: ForStoringNotes): NoteCreator = NoteCreator(forStoringNotes)
}
