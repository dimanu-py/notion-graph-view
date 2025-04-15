from src.graph.notes import RelatedNotesIds
from tests.shared.domain.random_generator import RandomGenerator


class RelatedNotesMother:
    @classmethod
    def create(cls, number_notes: int = 1) -> RelatedNotesIds:
        return RelatedNotesIds([RandomGenerator.uuid() for _ in range(number_notes)])
