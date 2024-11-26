from fastapi import FastAPI

from src.contexts.graph.notes.application.all_notes_finder import AllNotesFinder
from src.contexts.graph.notes.domain.notes_repository import NotesRepository


app = FastAPI()


@app.get("/graph/{database_id}")
async def get_database_notes(database_id: str) -> dict:
    notes_finder = AllNotesFinder(repository=NotesRepository())
    notes = notes_finder(database_id)
    return notes
