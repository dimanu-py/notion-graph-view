package com.dimanupy.backend.config

import com.dimanupy.notes.note.infra.HttpNotionRepository
import com.dimanupy.notes.note.infra.NotionConnectionData
import org.http4k.client.JavaHttpClient
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NotionConfig {
    @Bean
    @ConfigurationProperties(prefix = "notion.integration")
    fun notionConnectionData() = NotionConnectionData()

    @Bean
    fun notionRepository(notionConnectionData: NotionConnectionData) = HttpNotionRepository(
        client = JavaHttpClient(),
        connectionData = notionConnectionData
    )
}