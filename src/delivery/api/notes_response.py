from typing import List

from pydantic import BaseModel


class NoteResponse(BaseModel):
    id: str
    url: str
    title: str
    related_notes: dict


class NotesResponse(BaseModel):
    notes: List[NoteResponse]
