import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/button/src/vaadin-button.js';
import '@vaadin/select/src/vaadin-select.js';
import '@vaadin/list-box/src/vaadin-list-box.js';
import '@vaadin/item/src/vaadin-item.js';
import '@vaadin/grid/src/vaadin-grid.js';

@customElement('manage-users-form')
export class ManageUsersForm extends LitElement {
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
<vaadin-vertical-layout style="width: 100%; height: 100%; align-items: center;">
 <vaadin-horizontal-layout theme="spacing-xs" style="width: 100%; flex-shrink: 1; flex-grow: 0; align-self: center;">
  <label style="align-self: center;">Admin role: </label>
  <vaadin-select value="Item one" id="selectRole">
   <template>
    <vaadin-list-box>
     <vaadin-item>
       Item one 
     </vaadin-item>
     <vaadin-item>
       Item two 
     </vaadin-item>
     <vaadin-item>
       Item three 
     </vaadin-item>
    </vaadin-list-box>
   </template>
  </vaadin-select>
  <vaadin-button id="buttonUpdate">
   Nastavit
  </vaadin-button>
 </vaadin-horizontal-layout>
 <vaadin-grid id="gridUsers" is-attached></vaadin-grid>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
