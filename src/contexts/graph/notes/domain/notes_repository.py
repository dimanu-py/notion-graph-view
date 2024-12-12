from abc import ABC, abstractmethod


class NotesRepository(ABC):
    @abstractmethod
    def search(self, database_id: str) -> dict:
        raise NotImplementedError
