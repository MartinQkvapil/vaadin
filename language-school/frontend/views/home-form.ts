import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/grid/src/vaadin-grid.js';
import '@vaadin/form-layout/src/vaadin-form-layout.js';
import '@vaadin/button/src/vaadin-button.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/password-field/src/vaadin-password-field.js';

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
<vaadin-vertical-layout style="height: 100%;">
 <h1 style="align-self: center;">LANGUAGE SCHOOL - jazyky pro každého.</h1>
 <h3 style="margin: var(--lumo-space-xs); padding: var(--lumo-space-xs);"> Jaké jazyky vyučujeme? </h3>
 <vaadin-grid id="languageGrid" style="flex-grow: 0; flex-shrink: 1; align-self: center; height: 100%;" is-attached all-rows-visible></vaadin-grid>
 <h3 style="margin: var(--lumo-space-xs); padding: var(--lumo-space-xs);"> Objednejte si u nás kurz!</h3>
 <vaadin-form-layout>
  <vaadin-form-item>
   <label slot="label">Jazyk:</label>
   <vaadin-select id="selectLanguages"></vaadin-select>
  </vaadin-form-item>
  <vaadin-form-item>
   <label slot="label">Email:</label>
   <vaadin-text-field id="inputEmail" type="text"></vaadin-text-field>
  </vaadin-form-item>
  <vaadin-form-item>
   <label slot="label">Jméno:</label>
   <vaadin-text-field type="text" id="inputName"></vaadin-text-field>
  </vaadin-form-item>
  <vaadin-form-item>
   <label slot="label">Příjmení:</label>
   <vaadin-text-field type="text" id="inputSurname"></vaadin-text-field>
  </vaadin-form-item>
  <vaadin-form-item>
   <label slot="label">Login:</label>
   <vaadin-text-field type="text" id="inputLogin"></vaadin-text-field>
  </vaadin-form-item>
  <vaadin-form-item>
   <label slot="label">Heslo:</label>
   <vaadin-password-field id="inputPassword" type="password"></vaadin-password-field>
  </vaadin-form-item>
 </vaadin-form-layout>
 <vaadin-button id="orderCourseButton" style="align-self: center; width: 50%;">
  Objednat kurz
 </vaadin-button>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
