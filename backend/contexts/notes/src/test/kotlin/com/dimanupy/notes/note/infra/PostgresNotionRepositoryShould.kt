package com.dimanupy.notes.note.infra

import com.dimanupy.notes.note.domain.NoteMother
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.Test
import kotlin.test.assertEquals

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostgresNotionRepositoryShould {

    private lateinit var postgresRepository: PostgresNotesRepository

    companion object {
        @Container
        private val postgresContainer = PostgreSQLContainer<Nothing>("postgres:14").apply {
            withDatabaseName("notes_test")
            withUsername("test")
            withPassword("test")
            start()
        }
    }

    @BeforeAll
    fun setup() {
        Database.connect(
            url = postgresContainer.jdbcUrl,
            user = postgresContainer.username,
            password = postgresContainer.password,
        )
        transaction {
            SchemaUtils.create(NotesModel)
        }

        postgresRepository = PostgresNotesRepository()
    }

    @AfterEach
    fun cleanup() = transaction { SchemaUtils.drop(NotesModel) }

    @AfterAll
    fun tearDown() = postgresContainer.stop()

    @Test
    fun `should save Notion notes`() {
        val notes = listOf(NoteMother.any(), NoteMother.any(), NoteMother.any())

        postgresRepository.save(notes)

        val savedNotes = postgresRepository.findAll()
        assert(savedNotes.isNotEmpty())
        assertEquals(savedNotes, notes)
    }
}
