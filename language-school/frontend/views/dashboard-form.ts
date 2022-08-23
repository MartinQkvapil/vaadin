import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
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
 <div id="divChart" style="align-self: center; width: 90%; margin: var(--lumo-space-s); height: 100%;"></div>
 <vaadin-grid id="gridTests" style="height: 100%; width: 90%; align-self: center;" is-attached></vaadin-grid>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
