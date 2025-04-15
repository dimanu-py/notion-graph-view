from src.graph.notes import NoteUrl
from tests.shared.domain.random_generator import RandomGenerator


class NoteUrlMother:
    @classmethod
    def create(cls) -> NoteUrl:
        return NoteUrl(RandomGenerator.url())
