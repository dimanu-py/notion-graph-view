import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { NOTION_GRAPH_REPOSITORY } from './domain/notion-graph/notion-graph.repository';
import { NotionGraphApiRepository } from './infrastructure/notion-graph/notion-graph-api.repository';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(),
    { provide: NOTION_GRAPH_REPOSITORY, useClass: NotionGraphApiRepository },
  ],
};
