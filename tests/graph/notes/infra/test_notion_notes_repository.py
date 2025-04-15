import os

import pytest
from dotenv import load_dotenv
from expects import expect, equal

from src.graph.notes.infra.notion_client import NotionClient
from src.graph.notes import NotionNotesRepository
from tests.graph.notes.domain.note_mother import NoteMother
from tests.graph.notes.domain.notes_database_id_mother import (
    NotesDatabaseIdMother,
)

load_dotenv()


@pytest.mark.integration
class TestNotionNotesRepository:
    def setup_method(self) -> None:
        self.notion_client = NotionClient(auth_token=os.environ["NOTION_API_KEY"])
        self.repository = NotionNotesRepository(client=self.notion_client)

    def test_should_search_all_notes(self) -> None:
        note_one = NoteMother.create(
            {
                "id": "15bf5bab-5d4e-807b-bf58-ca937660b2fb",
                "url": "https://www.notion.so/Note-test-integration-2-15bf5bab5d4e807bbf58ca937660b2fb",
                "title": "Note test integration 2",
                "related_notes": ["15bf5bab-5d4e-8064-b9ce-dd66d2024fc6"],
            }
        )
        note_two = NoteMother.create(
            {
                "id": "15bf5bab-5d4e-8064-b9ce-dd66d2024fc6",
                "url": "https://www.notion.so/Note-test-integration-1-15bf5bab5d4e8064b9cedd66d2024fc6",
                "title": "Note test integration 1",
                "related_notes": ["15bf5bab-5d4e-807b-bf58-ca937660b2fb"],
            }
        )
        expected_notes = [note_one, note_two]
        database_id = NotesDatabaseIdMother.create(
            os.environ["NOTION_TEST_DATABASE_ID"]
        )

        notes = self.repository.search(database_id=database_id)

        expect(notes).to(equal(expected_notes))
