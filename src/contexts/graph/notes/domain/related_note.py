from src.contexts.graph.notes.domain.note_id import NoteId


class RelatedNote:
    def __init__(self, id_: NoteId) -> None:
        self._id = id_

    @classmethod
    def create(cls, id_: str) -> "RelatedNote":
        return RelatedNote(id_=NoteId(id_))

    @property
    def id(self) -> NoteId:
        return self._id
