from src.contexts.graph.notes.domain.notes_repository import NotesRepository


class NotionNotesRepository(NotesRepository):
    def __init__(self) -> None:
        pass

    def search(self, database_id: str) -> dict:
        raise NotImplementedError
