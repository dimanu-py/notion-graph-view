from src.contexts.graph.notes.domain.note_id import NoteId
from src.contexts.graph.notes.domain.note_title import NoteTitle
from src.contexts.graph.notes.domain.note_url import NoteUrl
from src.contexts.graph.notes.domain.notes import Notes


class Note:
    def __init__(
        self, id_: NoteId, title: NoteTitle, url: NoteUrl, related_notes: Notes
    ) -> None:
        self._related_notes = related_notes
        self._url = url
        self._title = title
        self._id = id_

    @classmethod
    def create(
        cls, id_: str, title: str, url: str, related_notes: list[dict[str, str]]
    ) -> "Note":
        raise NotImplementedError
