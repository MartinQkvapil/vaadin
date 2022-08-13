import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/form-layout/src/vaadin-form-layout.js';
import '@vaadin/select/src/vaadin-select.js';
import '@vaadin/button/src/vaadin-button.js';
import '@vaadin/item/src/vaadin-item.js';
import '@vaadin/list-box/src/vaadin-list-box.js';

@customElement('home-form')
export class HomeForm extends LitElement {
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
 <div style="align-self: center;">
   Language school 
 </div>
 <vaadin-form-layout>
  <vaadin-select value="Item one" id="teaching-languages-select">
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
  <vaadin-button id="vaadinButton">
   Objednat kurz
  </vaadin-button>
 </vaadin-form-layout>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
