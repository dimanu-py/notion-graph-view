package com.dimanupy.backend.config

import com.dimanupy.backend.driven.forCommunicatingWithNotion.HttpForCommunicatingWithNotion
import com.dimanupy.backend.driven.forCommunicatingWithNotion.NotionConnectionData
import com.dimanupy.backend.driven.forStoringNotes.PostgresForStoringNotes
import com.dimanupy.backend.graph.driven.forCommunicatingWithNotion.ForCommunicatingWithNotion
import com.dimanupy.backend.graph.driven.forStoringNotes.ForStoringNotes
import com.dimanupy.backend.graph.driving.forManagingNotes.createNote.NoteCreator
import com.dimanupy.backend.graph.driving.forManagingNotes.syncNotionNotes.NotesSyncer
import org.http4k.client.JavaHttpClient
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyInjectorConfig {
    @Bean
    @ConfigurationProperties(prefix = "notion.integration")
    fun notionConnectionData() = NotionConnectionData()

    @Bean
    fun forCommunicatingWithNotion(notionConnectionData: NotionConnectionData) = HttpForCommunicatingWithNotion(
        client = JavaHttpClient(),
        connectionData = notionConnectionData,
    )

    @Bean
    fun forStoringNotes() = PostgresForStoringNotes()

    @Bean
    fun notesSyncer(
        forCommunicatingWithNotion: ForCommunicatingWithNotion,
        forStoringNotes: ForStoringNotes,
    ): NotesSyncer = NotesSyncer(forCommunicatingWithNotion, forStoringNotes)

    @Bean
    fun notesCreator(forStoringNotes: ForStoringNotes): NoteCreator = NoteCreator(forStoringNotes)
}
