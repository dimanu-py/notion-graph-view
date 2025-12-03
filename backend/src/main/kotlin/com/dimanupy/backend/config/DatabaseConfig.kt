package com.dimanupy.backend.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseConfig {
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

