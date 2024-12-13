from src.contexts.graph.notes.domain.related_notes_ids import RelatedNotesIds
from tests.contexts.shared.domain.random_generator import RandomGenerator


class RelatedNotesMother:
    @classmethod
    def create(cls, number_notes: int = 1) -> RelatedNotesIds:
        return RelatedNotesIds([RandomGenerator.uuid() for _ in range(number_notes)])
