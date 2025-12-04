import { Component } from '@angular/core';

@Component({
  selector: 'app-graph-view-page',
  standalone: true,
  template: `
    <section class="graph-view">
      <header class="graph-toolbar">
        <div class="search-panel">
          <input
            class="search-input"
            type="search"
            placeholder="Search notes..."
          />
        </div>
        <button
          class="refresh-button"
          type="button"
          (click)="onRefreshClick()"
          [disabled]="isRefreshing"
        >
          ⟳
        </button>
      </header>

      <div class="graph-canvas">
        <div class="graph-node"></div>
        <div class="graph-node"></div>
        <div class="graph-node"></div>
      </div>
    </section>
  `,
  styles: [
    `
      .graph-view {
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        padding: 1.5rem 2rem 2rem;
        box-sizing: border-box;
        color: #f5f5ff;
      }

      .graph-toolbar {
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-bottom: 1.5rem;
      }

      .search-panel {
        max-width: 360px;
        width: 100%;
      }

      .search-input {
        width: 100%;
        padding: 0.75rem 1rem;
        padding-left: 2.5rem;
        border-radius: 999px;
        border: 1px solid rgba(255, 255, 255, 0.12);
        background: rgba(4, 6, 24, 0.72);
        color: inherit;
        outline: none;
        box-shadow: 0 0 0 1px rgba(255, 255, 255, 0.02);
      }

      .search-input::placeholder {
        color: rgba(235, 235, 255, 0.6);
      }

      .refresh-button {
        width: 44px;
        height: 44px;
        border-radius: 999px;
        border: 1px solid rgba(255, 255, 255, 0.18);
        background: rgba(6, 10, 40, 0.85);
        color: #ffffff;
        cursor: pointer;
        display: flex;
        align-items: center;
        justify-content: center;
        box-shadow: 0 0 18px rgba(120, 187, 255, 0.4);
      }

      .refresh-button:disabled {
        opacity: 0.6;
        cursor: default;
        box-shadow: none;
      }

      .graph-canvas {
        position: relative;
        flex: 1;
        margin-top: 0.75rem;
      }

      .graph-node {
        position: absolute;
        width: 8px;
        height: 8px;
        border-radius: 999px;
        background: #ffffff;
        box-shadow:
          0 0 4px rgba(255, 255, 255, 0.9),
          0 0 14px rgba(115, 177, 255, 0.85),
          0 0 28px rgba(115, 177, 255, 0.65);
      }

      .graph-node:nth-child(1) {
        top: 24%;
        left: 36%;
      }

      .graph-node:nth-child(2) {
        top: 52%;
        left: 62%;
      }

      .graph-node:nth-child(3) {
        top: 70%;
        left: 28%;
      }
    `,
  ],
})
export class GraphViewPageComponent {
  isRefreshing = false;

  onRefreshClick(): void {
    if (this.isRefreshing) {
      return;
    }

    this.isRefreshing = true;
    // Placeholder for future backend call to refresh the Notion database.
    // For now, this just simulates a short refresh cycle.
    setTimeout(() => {
      console.log('[GraphView] Refresh requested');
      this.isRefreshing = false;
    }, 600);
  }
}


