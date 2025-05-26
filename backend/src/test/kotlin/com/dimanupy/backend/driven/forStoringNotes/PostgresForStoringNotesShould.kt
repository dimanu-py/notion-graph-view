package com.dimanupy.backend.driven.forStoringNotes

import com.dimanupy.backend.graph.note.Note
import com.dimanupy.backend.graph.note.NoteMother
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
class PostgresForStoringNotesShould {

    private lateinit var postgresRepository: PostgresForStoringNotes

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

        postgresRepository = PostgresForStoringNotes()
    }

    @AfterEach
    fun cleanup() = transaction { SchemaUtils.drop(NotesModel) }

    @AfterAll
    fun tearDown() = postgresContainer.stop()

    @Test
    fun `should save Notion notes`() {
        val notes = listOf(
            Note.fromPrimitives(NoteMother.create()),
            Note.fromPrimitives(NoteMother.create()),
            Note.fromPrimitives(NoteMother.create()),
        )

        postgresRepository.save(notes)

        val savedNotes = postgresRepository.findAll()
        assert(savedNotes.isNotEmpty())
        assertEquals(savedNotes, notes)
    }

    @Test
    fun `should save note`() {
        val note = Note.fromPrimitives(NoteMother.create())

        postgresRepository.save(note)

        val savedNotes = postgresRepository.findAll()
        assert(savedNotes.isNotEmpty())
        assertEquals(savedNotes.first(), note)
    }

}
