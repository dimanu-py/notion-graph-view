package com.dimanupy.backend.driving.forManagingNotes.createNote

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateNotesControllerShould {

    @LocalServerPort
    private val port: Int = 0

    private lateinit var requestBody: String
    private lateinit var response: Response

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
    }

    @Test
    fun `save valid note`() {
        givenAValidNoteRequest()

        whenICreateANote()

        thenStatusCodeIs(200)
    }

    @Test
    fun `throw error if note request has empty url`() {
        givenANoteRequestWithEmptyUrl()

        whenICreateANote()

        thenStatusCodeIs(400)
        thenResponseBodyContains(
            "Bad Request",
            "Note URL cannot be empty",
            "/notes",
        )
    }

    private fun whenICreateANote() {
        response = Given {
            contentType(ContentType.JSON)
            body(requestBody)
        } When {
            post("/notes")
        }
    }

    private fun thenStatusCodeIs(statusCode: Int) {
        response.Then {
            statusCode(statusCode)
        }
    }

    private fun givenAValidNoteRequest() {
        requestBody = """
            {
                "id": "feb562ba-434a-45a0-93e0-4a233e31989c",
                "title": "Test Note",
                "url": "https://www.notion.so/Test-Note-feb562ba-434a-45a0-93e0-4a233e31989c",
                "related_notes": ["1f91481c-2f0c-4b23-8d50-e82ca05be8a2"]
            }
        """.trimIndent()
    }

    private fun givenANoteRequestWithEmptyUrl() {
        requestBody = """
            {
                "id": "feb562ba-434a-45a0-93e0-4a233e31989c",
                "title": "Test Note",
                "url": " ",
                "related_notes": ["1f91481c-2f0c-4b23-8d50-e82ca05be8a2"]
            }
        """.trimIndent()
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
