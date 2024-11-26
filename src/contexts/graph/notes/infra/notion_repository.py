from src.contexts.graph.notes.domain.notes_repository import NotesRepository


class NotionRepository(NotesRepository):
    def search(self, database_id: str) -> dict:
        raise NotImplementedError
