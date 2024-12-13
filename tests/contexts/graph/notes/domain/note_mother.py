from src.contexts.graph.notes.domain.note import Note
from tests.contexts.graph.notes.domain.note_id_mother import NoteIdMother
from tests.contexts.graph.notes.domain.note_title_mother import NoteTitleMother
from tests.contexts.graph.notes.domain.note_url_mother import NoteUrlMother
from tests.contexts.graph.notes.domain.related_notes_mother import RelatedNotesMother


class NoteMother:
    @classmethod
    def create(cls, params: dict) -> Note:
        primitives = {
            "id": NoteIdMother.create().value,
            "url": NoteUrlMother.create().value,
            "title": NoteTitleMother.create().value,
            "related_notes": RelatedNotesMother.create().value,
            **(params if params else {}),
        }

        return Note.create(
            id_=primitives["id"],
            url=primitives["url"],
            title=primitives["title"],
            related_notes=primitives["related_notes"],
        )
