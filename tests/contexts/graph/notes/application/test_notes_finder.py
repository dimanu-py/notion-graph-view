from doublex import Spy, assert_that, called

from src.contexts.graph.notes.application.notes_finder import NotesFinder
from src.contexts.graph.notes.domain.notes_repository import NotesRepository


class TestNotesFinder:
    ANY_ID = "any_id"

    def test_finds_notion_notes(self) -> None:
        repository = Spy(NotesRepository)
        notes_finder = NotesFinder(repository)

        notes_finder(database_id=self.ANY_ID)

        assert_that(repository.search, called().with_args(self.ANY_ID))
