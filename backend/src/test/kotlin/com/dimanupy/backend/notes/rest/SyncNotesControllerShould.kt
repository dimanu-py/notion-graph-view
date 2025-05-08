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
    private lateinit var response: Response

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
    }

    @Test
    fun `get current notes from valid Notion database`() {
        givenAValidNotionDatabaseId()

        whenIFetchDatabaseNotesFromNotion()

        thenStatusCodeIsOk()
    }

    @Test
    fun `report error when Notion database has no integration`() {
        givenANotionDatabaseIdWithNoIntegration()

        whenIFetchDatabaseNotesFromNotion()

        thenStatusCodeIs(400)
    }

    private fun givenAValidNotionDatabaseId() {
        databaseId = System.getenv("TEST_DATABASE_ID")
    }

    private fun whenIFetchDatabaseNotesFromNotion() {
        response = When {
            put("/notes/sync/$databaseId")
        }
    }

    private fun thenStatusCodeIsOk() {
        response.Then {
            statusCode(200)
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
}
