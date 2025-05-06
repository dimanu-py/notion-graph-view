package com.dimanupy.backend.notes.rest

import io.restassured.RestAssured
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SyncNotesControllerShould {

    @LocalServerPort
    private val port: Int = 0

    private lateinit var databaseId: String

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
    }

    @Test
    fun `should get current notes from valid Notion database`() {
        givenAValidNotionDatabaseId()

        val response = whenIFetchDatabaseNotesFromNotion()

        thenStatusCodeIsOk(response)
    }

    private fun givenAValidNotionDatabaseId() {
        databaseId = System.getenv("TEST_DATABASE_ID")
    }

    private fun whenIFetchDatabaseNotesFromNotion(): Response = When {
        put("/notes/sync/$databaseId")
    }

    private fun thenStatusCodeIsOk(response: Response) {
        response.Then {
            statusCode(200)
        }
    }
}
