package com.dimanupy.notes.shared.domain

import com.github.javafaker.Faker
import java.util.*

object RandomGenerator {
    private val faker = Faker()

    fun title(): String = faker.book().title()

    fun notionUrl(): String = "https://www.notion.so/${faker.internet().slug()}"

    fun uuid(): String = UUID.randomUUID().toString()
}
