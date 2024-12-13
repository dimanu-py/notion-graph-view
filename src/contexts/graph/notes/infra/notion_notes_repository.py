from src.contexts.graph.notes.domain.notes_database_id import NotesDatabaseId
from src.contexts.graph.notes.domain.notes_repository import NotesRepository
from src.contexts.graph.notes.infra.notion_client import NotionClient


class NotionNotesRepository(NotesRepository):
    _client: NotionClient

    def __init__(self, client: NotionClient) -> None:
        self._client = client

    def search(self, database_id: NotesDatabaseId) -> dict[str, list[dict]]:
        raw_notes = self._client.databases.query(database_id=database_id.value)

        notes = []
        for result in raw_notes.get("results", []):  # type: ignore
            filtered_result = {
                "id": result.get("id"),
                "properties": {
                    "Related Notes": result["properties"]["Related Notes"],
                    "Name": result["properties"]["Name"],
                },
                "url": result.get("url"),
                "public_url": result.get("public_url"),
            }
            notes.append(filtered_result)

        return {"results": notes}
