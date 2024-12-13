import os

from fastapi import APIRouter, Depends, status
from fastapi.responses import JSONResponse

from src.contexts.graph.notes.application.all_notes_finder import AllNotesFinder
from src.contexts.graph.notes.infra.notion_client import NotionClient
from src.contexts.graph.notes.infra.notion_notes_repository import NotionNotesRepository
from src.delivery.api.notes_response import NotesResponse, NoteResponse

router = APIRouter(prefix="/notes", tags=["Notes"])


def inject_client() -> NotionClient:
    return NotionClient(auth_token=os.environ["NOTION_API_KEY"])


@router.get("/{database_id}")
async def get_database_notes(
    database_id: str, client: NotionClient = Depends(inject_client)
) -> JSONResponse:
    notes_finder = AllNotesFinder(repository=NotionNotesRepository(client=client))

    notes = notes_finder(database_id)

    primitive_notes = [NoteResponse(**note.to_dict()) for note in notes]
    response = NotesResponse(notes=primitive_notes)
    return JSONResponse(content=response.model_dump(), status_code=status.HTTP_200_OK)
