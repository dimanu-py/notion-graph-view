import json
import os

import pytest
from dotenv import load_dotenv
from expects import expect, equal

from src.contexts.graph.notes.infra.notion_client import NotionClient
from src.contexts.graph.notes.infra.notion_notes_repository import NotionNotesRepository

load_dotenv()


@pytest.mark.integration
class TestNotionNotesRepository:
    def setup_method(self) -> None:
        self.notion_client = NotionClient(auth_token=os.environ["NOTION_API_KEY"])
        self.repository = NotionNotesRepository(client=self.notion_client)

    def test_should_search_all_notes(self) -> None:
        with open("notes.json") as file:
            expected_notes = json.load(file)

        database_id = os.environ["NOTION_TEST_DATABASE_ID"]

        notes = self.repository.search(database_id=database_id)

        expect(notes).to(equal(expected_notes))
