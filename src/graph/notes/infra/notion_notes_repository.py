from src.graph.notes.domain.note import Note
from src.graph.notes.domain.notes_database_id import NotesDatabaseId
from src.graph.notes.domain.notes_repository import NotesRepository
from src.graph.notes.infra.notion_client import NotionClient


class NotionNotesRepository(NotesRepository):
    _client: NotionClient

    def __init__(self, client: NotionClient) -> None:
        self._client = client

    def search(self, database_id: NotesDatabaseId) -> list[Note]:
        raw_notes = self._client.databases.query(database_id=database_id.value)

        return [
            self._create_note_from_json(note)
            for note in raw_notes["results"]  # type: ignore
        ]

    @staticmethod
    def _create_note_from_json(note: dict) -> Note:
        id_ = note["id"]
        url = note["url"]
        title = note["properties"]["Name"]["title"][0]["text"]["content"]
        related_notes = [
            relation["id"]
            for relation in note["properties"]["Related Notes"]["relation"]
        ]

        return Note.create(id_=id_, url=url, title=title, related_notes=related_notes)
