from src.contexts.graph.notes.domain.notes_database_id import NotesDatabaseId
from tests.contexts.shared.domain.random_generator import RandomGenerator


class NotesDatabaseIdMother:
    @classmethod
    def create(cls) -> NotesDatabaseId:
        return NotesDatabaseId(RandomGenerator.uuid())
