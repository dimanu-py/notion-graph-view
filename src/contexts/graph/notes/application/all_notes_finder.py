from src.contexts.graph.notes.domain.notes_repository import NotesRepository


class AllNotesFinder:
    repository: NotesRepository

    def __init__(self, repository: NotesRepository) -> None:
        self.repository = repository

    def __call__(self, database_id: str) -> dict:
        raise NotImplementedError
