from src.contexts.graph.notes.application.all_notes_finder import AllNotesFinder
from tests.contexts.graph.notes.domain.notes_database_id_mother import (
    NotesDatabaseIdMother,
)
from tests.contexts.graph.notes.notes_module_unit_test_config import (
    NotesModuleUnitTestConfig,
)


class TestAllNotesFinder(NotesModuleUnitTestConfig):
    def setup_method(self) -> None:
        self.finder = AllNotesFinder(repository=self.repository)

    def test_should_find_all_notes_in_a_database(self) -> None:
        database_id = NotesDatabaseIdMother.create()
        self.should_search(database_id=database_id)

        self.finder(database_id=database_id.value)

        self.should_have_searched()
