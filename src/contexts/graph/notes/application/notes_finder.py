from src.contexts.graph.notes.domain.notes_repository import NotesRepository


class NotesFinder:
    repository: NotesRepository

    def __init__(self, repository: NotesRepository) -> None:
        self.repository = repository

    def __call__(self, database_id: str) -> dict:
        return {}
