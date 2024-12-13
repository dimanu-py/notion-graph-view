from src.contexts.graph.notes.domain.note_url import NoteUrl
from tests.contexts.shared.domain.random_generator import RandomGenerator


class NoteUrlMother:
    @classmethod
    def create(cls) -> NoteUrl:
        return NoteUrl(RandomGenerator.url())
