from src.contexts.graph.notes.domain.note import Note
from src.contexts.graph.notes.domain.notes_database_id import NotesDatabaseId
from src.contexts.graph.notes.domain.notes_repository import NotesRepository
from src.contexts.graph.notes.infra.notion_client import NotionClient


class NotionNotesRepository(NotesRepository):
    _client: NotionClient

    def __init__(self, client: NotionClient) -> None:
        self._client = client

    def search(self, database_id: NotesDatabaseId) -> list[Note]:
        raw_notes = self._client.databases.query(database_id=database_id.value)

        notes = []
        for result in raw_notes.get("results", []):  # type: ignore
            note = Note.create(
                id_=result["id"],
                url=result["url"],
                title=result["properties"]["Name"]["title"][0]["plain_text"],
                related_notes=result["properties"]["Related Notes"],
            )
            notes.append(note)

        return notes
