import { InjectionToken } from '@angular/core';
import { Observable } from 'rxjs';

import { NotionGraph } from './notion-graph';

export interface NotionGraphRepository {
    loadGraph(): Observable<NotionGraph>;
}

export const NOTION_GRAPH_REPOSITORY = new InjectionToken<NotionGraphRepository>('NotionGraphRepository');

