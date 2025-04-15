from src.graph.notes.domain.note_id import NoteId
from tests.shared.domain.random_generator import RandomGenerator


class NoteIdMother:
    @classmethod
    def create(cls) -> NoteId:
        return NoteId(RandomGenerator.uuid())
