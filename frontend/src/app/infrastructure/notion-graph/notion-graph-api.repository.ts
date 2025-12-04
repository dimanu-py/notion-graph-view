import { HttpClient } from '@angular/common/http';
import { inject } from '@angular/core';
import { map } from 'rxjs';

import { NotionGraph } from '../../domain/notion-graph/notion-graph';
import { NotionGraphRepository } from '../../domain/notion-graph/notion-graph.repository';
import { Note } from '../../domain/notion-graph/note';
import { environment } from '../../../environments/environment';

type NoteResponse = {
    id: string;
    title: string;
    url: string;
    related_notes: string[];
};

export class NotionGraphApiRepository implements NotionGraphRepository {
    private readonly http = inject(HttpClient);
    private readonly endpoint = `${environment.apiBaseUrl}/notes`;

    loadGraph() {
        return this.http.get<NoteResponse[]>(this.endpoint).pipe(
            map((notes) => ({
                notes: notes.map(this.mapNoteResponse),
            })),
        );
    }

    private mapNoteResponse(note: NoteResponse): Note {
        return {
            id: note.id,
            title: note.title,
            url: note.url,
            relatedNoteIds: note.related_notes ?? [],
        };
    }
}

