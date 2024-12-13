from src.contexts.graph.notes.domain.notes_database_id import NotesDatabaseId
from tests.contexts.shared.domain.random_generator import RandomGenerator


class NotesDatabaseIdMother:
    @classmethod
    def create(cls, value: str | None = None) -> NotesDatabaseId:
        uuid = value if value else RandomGenerator.uuid()
        return NotesDatabaseId(uuid)
