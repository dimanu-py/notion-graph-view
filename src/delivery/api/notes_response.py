from typing import List

from pydantic import BaseModel


class NoteResponse(BaseModel):
    id: str
    url: str
    title: str
    related_notes: list[str]


class NotesResponse(BaseModel):
    notes: List[NoteResponse]
