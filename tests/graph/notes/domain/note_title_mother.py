from src.graph.notes.domain.note_title import NoteTitle
from tests.shared.domain.random_generator import RandomGenerator


class NoteTitleMother:
    @classmethod
    def create(cls) -> NoteTitle:
        return NoteTitle(RandomGenerator.title())
