from src.contexts.graph.notes.domain.note import Note
from src.contexts.graph.notes.domain.notes_database_id import NotesDatabaseId
from src.contexts.graph.notes.domain.notes_repository import NotesRepository


class AllNotesFinder:
    _repository: NotesRepository

    def __init__(self, repository: NotesRepository) -> None:
        self._repository = repository

    def __call__(self, database_id: str) -> list[Note]:
        return self._repository.search(database_id=NotesDatabaseId(database_id))
