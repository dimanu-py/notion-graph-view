import pytest
from doublex import Mock
from doublex_expects import have_been_satisfied
from expects import expect

from src.contexts.graph.notes.domain.notes_repository import NotesRepository


@pytest.mark.unit
class NotesModuleUnitTestConfig:
    repository = Mock(NotesRepository)

    def should_search(self, database_id: str) -> None:
        with self.repository as mock:
            mock.search(database_id)

    def should_have_searched(self) -> None:
        expect(self.repository).to(have_been_satisfied)
