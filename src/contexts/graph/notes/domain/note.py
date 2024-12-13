from typing import override

from src.contexts.graph.notes.domain.note_id import NoteId
from src.contexts.graph.notes.domain.note_title import NoteTitle
from src.contexts.graph.notes.domain.note_url import NoteUrl
from src.contexts.graph.notes.domain.related_notes_ids import RelatedNotesIds


class Note:
    _related_notes: RelatedNotesIds
    _title: NoteTitle
    _url: NoteUrl
    _id: NoteId

    def __init__(
        self,
        id_: NoteId,
        url: NoteUrl,
        title: NoteTitle,
        related_notes: RelatedNotesIds,
    ) -> None:
        self._id = id_
        self._url = url
        self._title = title
        self._related_notes = related_notes

    @classmethod
    def create(cls, id_: str, url: str, title: str, related_notes: list[str]) -> "Note":
        return Note(
            id_=NoteId(id_),
            url=NoteUrl(url),
            title=NoteTitle(title),
            related_notes=RelatedNotesIds(related_notes),
        )

    def to_primitives(self) -> dict:
        return {
            "id": self._id.value,
            "url": self._url.value,
            "title": self._title.value,
            "related_notes": self._related_notes.value,
        }

    @override
    def __repr__(self) -> str:
        return f"Note(id={self._id.value}, title={self._title.value})"

    @override
    def __eq__(self, other: "Note") -> bool:
        return self._id == other._id
