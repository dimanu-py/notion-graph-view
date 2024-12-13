import os

from src.contexts.graph.notes.application.all_notes_finder import AllNotesFinder
from src.contexts.graph.notes.infra.notion_client import NotionClient
from src.contexts.graph.notes.infra.notion_notes_repository import NotionNotesRepository

app = FastAPI()

load_dotenv()


def inject_client() -> NotionClient:
    return NotionClient(auth_token=os.environ["NOTION_API_KEY"])


@app.get("/graph/{database_id}")
async def get_database_notes(
    database_id: str, client: NotionClient = Depends(inject_client)
) -> JSONResponse:
    notes_finder = AllNotesFinder(repository=NotionNotesRepository(client=client))

    notes = notes_finder(database_id)
    return notes
