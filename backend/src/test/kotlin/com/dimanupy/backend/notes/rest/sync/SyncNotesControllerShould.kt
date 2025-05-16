package com.dimanupy.backend.notes.rest.sync

import io.restassured.RestAssured
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SyncNotesControllerShould {

    @LocalServerPort
    private val port: Int = 0

    private lateinit var databaseId: String
    private lateinit var response: Response

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
    }

    @Test
    fun `get current notes from valid Notion database`() {
        givenAValidNotionDatabaseId()

        whenIFetchDatabaseNotesFromNotion()

        thenStatusCodeIs(200)
    }

    @Test
    fun `report error when Notion database has no integration`() {
        givenANotionDatabaseIdWithNoIntegration()

        whenIFetchDatabaseNotesFromNotion()

        thenStatusCodeIs(400)
        thenResponseBodyContains(
            "Bad Request",
            "Database with id $databaseId is not shared with your integration.",
            "/notes/sync/$databaseId",
        )
    }

    private fun givenAValidNotionDatabaseId() {
        databaseId = System.getenv("TEST_DATABASE_ID")
    }

    private fun whenIFetchDatabaseNotesFromNotion() {
        response = When {
            put("/notes/sync/$databaseId")
        }
    }

    private fun givenANotionDatabaseIdWithNoIntegration() {
        databaseId = "e69a585c-50a1-4877-915a-60d5b6492701"
    }

    private fun thenStatusCodeIs(statusCode: Int) {
        response.Then {
            statusCode(statusCode)
        }
    }

    private fun thenResponseBodyContains(error: String, message: String, path: String) {
        response.Then {
            body("occurredOn", CoreMatchers.notNullValue())
            body("error", CoreMatchers.equalTo(error))
            body("message", CoreMatchers.equalTo(message))
            body("path", CoreMatchers.equalTo(path))
        }
    }
}