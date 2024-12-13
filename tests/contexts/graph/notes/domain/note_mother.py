import json

from src.contexts.graph.notes.domain.note import Note


class NoteMother:
    @classmethod
    def create(cls, number: int) -> Note:
        with open("notes.json") as file:
            note = json.load(file)["results"][number]
        return Note.create(
            id_=note["id"],
            url=note["url"],
            title=note["properties"]["Name"]["title"][0]["plain_text"],
            related_notes=note["properties"]["Related Notes"],
        )
