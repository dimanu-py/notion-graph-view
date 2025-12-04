import { Routes } from '@angular/router';
import { GraphViewPageComponent } from './features/graph-view/graph-view-page.component';

export const routes: Routes = [
    {
        path: '',
        pathMatch: 'full',
        redirectTo: 'graph',
    },
    {
        path: 'graph',
        component: GraphViewPageComponent,
    },
];
