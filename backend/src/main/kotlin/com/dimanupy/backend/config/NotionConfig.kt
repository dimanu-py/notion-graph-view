package com.dimanupy.backend.config

import com.dimanupy.backend.driven.forStoringNotes.HttpNotionRepository
import com.dimanupy.backend.driven.forStoringNotes.NotionConnectionData
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.http4k.client.JavaHttpClient
import org.jetbrains.exposed.sql.Database
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
        connectionData = notionConnectionData,
    )

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    fun databaseConnectionData() = DatabaseConnectionData()

    @Bean
    fun database(databaseConnectionData: DatabaseConnectionData): Database {
        val hikariConfig = HikariConfig().apply {
            jdbcUrl = databaseConnectionData.url
            username = databaseConnectionData.username
            password = databaseConnectionData.password
            driverClassName = "org.postgresql.Driver"
            maximumPoolSize = 10
        }
        val dataSource = HikariDataSource(hikariConfig)
        return Database.connect(dataSource)
    }
}
