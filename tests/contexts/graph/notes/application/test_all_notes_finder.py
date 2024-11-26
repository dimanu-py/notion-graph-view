import pytest
from doublex import Spy, assert_that, called

from src.contexts.graph.notes.application.all_notes_finder import AllNotesFinder
from src.contexts.graph.notes.domain.notes_repository import NotesRepository


@pytest.mark.unit
class TestNotesFinder:
    ANY_ID = "any_id"

    @pytest.mark.xfail
    def test_finds_notion_notes(self) -> None:
        repository = Spy(NotesRepository)
        notes_finder = AllNotesFinder(repository)

        notes_finder(database_id=self.ANY_ID)

        assert_that(repository.search, called().with_args(self.ANY_ID))
