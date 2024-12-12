from fastapi import FastAPI

from src.contexts.graph.notes.application.all_notes_finder import AllNotesFinder
from src.contexts.graph.notes.infra.notion_notes_repository import NotionNotesRepository

app = FastAPI()


@app.get("/graph/{database_id}")
async def get_database_notes(database_id: str) -> dict:
    notes_finder = AllNotesFinder(repository=NotionNotesRepository())
    notes = notes_finder(database_id)
    return notes
