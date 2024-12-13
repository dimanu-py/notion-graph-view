from abc import ABC, abstractmethod

from src.contexts.graph.notes.domain.note import Note
from src.contexts.graph.notes.domain.notes_database_id import NotesDatabaseId


class NotesRepository(ABC):
    @abstractmethod
    def search(self, database_id: NotesDatabaseId) -> list[Note]:
        raise NotImplementedError
