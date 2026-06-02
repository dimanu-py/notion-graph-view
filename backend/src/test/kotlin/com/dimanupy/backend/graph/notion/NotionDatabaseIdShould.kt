package com.dimanupy.backend.graph.notion

import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class NotionDatabaseIdShould {
    
    @Test
    fun `accept UUID with hyphens`() {
        val uuidWithHyphens = "15bf5bab-5d4e-807b-bf58-ca937660b2fb"
        
        val notionDatabaseId = NotionDatabaseId(uuidWithHyphens)
        
        assertEquals(uuidWithHyphens, notionDatabaseId.value)
        assertEquals("15bf5bab5d4e807bbf58ca937660b2fb", notionDatabaseId.notionFormat)
    }
    
    @Test
    fun `accept UUID without hyphens (Notion format)`() {
        val uuidWithoutHyphens = "15bf5bab5d4e807bbf58ca937660b2fb"
        
        val notionDatabaseId = NotionDatabaseId(uuidWithoutHyphens)
        
        assertEquals(uuidWithoutHyphens, notionDatabaseId.value)
        assertEquals(uuidWithoutHyphens, notionDatabaseId.notionFormat)
    }
    
    @Test
    fun `throw InvalidDatabaseIdFormat when UUID is invalid`() {
        val invalidUuid = "not-a-valid-uuid"
        
        assertThrows<InvalidDatabaseIdFormat> {
            NotionDatabaseId(invalidUuid)
        }
    }
    
    @Test
    fun `throw InvalidDatabaseIdFormat when UUID without hyphens has wrong length`() {
        val invalidUuid = "15bf5bab5d4e807bbf58ca937660b2"  // 31 chars instead of 32
        
        assertThrows<InvalidDatabaseIdFormat> {
            NotionDatabaseId(invalidUuid)
        }
    }
}

