from src.contexts.graph.notes.domain.note_id import NoteId
from tests.contexts.shared.domain.random_generator import RandomGenerator


class NoteIdMother:
    @classmethod
    def create(cls) -> NoteId:
        return NoteId(RandomGenerator.uuid())
