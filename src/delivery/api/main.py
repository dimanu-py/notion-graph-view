from fastapi import FastAPI

from src.contexts.graph.notes.application.notes_finder import NotesFinder
from src.contexts.graph.notes.infra.notion_repository import NotionRepository

app = FastAPI()


@app.get("/graph/{database_id}")
async def get_database_notes(database_id: str) -> dict:
    notes_finder = NotesFinder(repository=NotionRepository())
    notes = notes_finder(database_id)
    return notes
