import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vaadin-form-layout/vaadin-form-layout.js';
import '@vaadin/select/src/vaadin-select.js';
import '@vaadin/vaadin-form-layout/vaadin-form-item.js';
import '@vaadin/button/src/vaadin-button.js';

@customElement('order-course-form')
export class OrderCourseForm extends LitElement {
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
<vaadin-form-layout style="width: 100%; height: 100%;">
 <vaadin-form-item>
  <label slot="label">Jazyk:</label>
  <vaadin-select id="selectLanguages"></vaadin-select>
 </vaadin-form-item>
 <vaadin-form-item id="idLogin">
  <label slot="label">Email:</label>
  <vaadin-text-field type="text" id="inputEmail"></vaadin-text-field>
 </vaadin-form-item>
 <vaadin-form-item>
  <label slot="label">Příjmení:</label>
  <vaadin-text-field type="text" id="inputSurname"></vaadin-text-field>
 </vaadin-form-item>
 <vaadin-form-item>
  <label slot="label">Jméno:</label>
  <vaadin-text-field type="text" id="inputName"></vaadin-text-field>
 </vaadin-form-item>
 <vaadin-form-item id="idLogin">
  <label slot="label">Login: </label>
  <vaadin-text-field type="text" id="inputLogin"></vaadin-text-field>
 </vaadin-form-item>
 <vaadin-form-item id="idLogin">
  <label slot="label">Heslo:</label>
  <vaadin-password-field id="inputPass" type="password"></vaadin-password-field>
 </vaadin-form-item>
 <vaadin-form-item id="idLogin">
  <label slot="label">Závazně:</label>
  <vaadin-button id="buttonOrderCourse" style="flex-grow: 1; width: 100%; flex-shrink: 0;">
   Objednat kurz 
  </vaadin-button>
 </vaadin-form-item>
</vaadin-form-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
