import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/button/src/vaadin-button.js';
import '@polymer/iron-icon/iron-icon.js';

@customElement('login-form')
export class LoginForm extends LitElement {
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
 <vaadin-button>
   Login 
 </vaadin-button>
 <vaadin-button theme="icon" aria-label="Add new">
  <iron-icon icon="lumo:plus"></iron-icon>
 </vaadin-button>
 <vaadin-button theme="primary error">
   Primary Error 
 </vaadin-button>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
