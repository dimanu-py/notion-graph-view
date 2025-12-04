export type NoteId = string;

export interface Note {
    id: NoteId;
    title: string;
    url: string;
    relatedNoteIds: NoteId[];
}

