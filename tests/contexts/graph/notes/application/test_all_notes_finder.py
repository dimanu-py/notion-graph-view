from src.contexts.graph.notes.application.all_notes_finder import AllNotesFinder
from tests.contexts.graph.notes.notes_module_unit_test_config import (
    NotesModuleUnitTestConfig,
)


class TestAllNotesFinder(NotesModuleUnitTestConfig):
    ANY_ID = "any_id"

    def setup_method(self) -> None:
        self.finder = AllNotesFinder(repository=self.repository)

    def test_should_find_all_notes_in_a_database(self) -> None:
        self.should_search(database_id=self.ANY_ID)

        self.finder(database_id=self.ANY_ID)

        self.should_have_searched()
