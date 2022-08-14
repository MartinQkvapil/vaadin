import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/charts/src/vaadin-chart.js';
import '@vaadin/grid/src/vaadin-grid.js';

@customElement('dashboard-form')
export class DashboardForm extends LitElement {
  static get styles() {
    return css`
      :host {
          display: block;
          height: 100%;
      }
      `;
  }

  render() {
    return html`
<vaadin-vertical-layout style="width: 100%; height: 100%;">
 <h1 style="align-self: center;">Přehled kurzu </h1>
 <vaadin-grid id="gridTests" style="height: 100%;" is-attached></vaadin-grid>
 <vaadin-chart type="column" title="Monthly Average Rainfall" subtitle="Source: WorldClimate.com" categories="" additional-options="" id="chartAnswers" style="align-self: center;">
  <vaadin-chart-series title="Tokyo" values="[49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]"></vaadin-chart-series>
  <vaadin-chart-series title="New York" values="[83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]"></vaadin-chart-series>
  <vaadin-chart-series title="London" values="[48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2]"></vaadin-chart-series>
  <vaadin-chart-series title="Berlin" values="[42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1]"></vaadin-chart-series>
 </vaadin-chart>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
