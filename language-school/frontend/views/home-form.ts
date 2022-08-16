import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/grid/src/vaadin-grid.js';
import '@vaadin/form-layout/src/vaadin-form-layout.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/password-field/src/vaadin-password-field.js';
import '@vaadin/button/src/vaadin-button.js';

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
<vaadin-vertical-layout style="height: 100%;" class="home-background">
 <h1 style="align-self: center; margin: var(--lumo-space-s); padding: var(--lumo-space-s);">JAZYKOVÁ ŠKOLA</h1>
 <div style="width: 90%; margin: var(--lumo-space-s); padding: var(--lumo-space-s); align-self: center;">
  <h3 style="margin: var(--lumo-space-s); padding: var(--lumo-space-s);"> Jaké jazyky vyučujeme? </h3>
  <vaadin-grid id="languageGrid" style="flex-grow: 0; flex-shrink: 1; align-self: center; margin: var(--lumo-space-s); padding: var(--lumo-space-s); width: 100%;" is-attached all-rows-visible page-size=""></vaadin-grid>
 </div>
 <div id="divOrderCourse" style="align-self: center; width: 90%;">
  <div style="width: 100%; margin: var(--lumo-space-s); padding: var(--lumo-space-s); align-self: center;">
   <vaadin-form-layout style="width: 100%;">
    <h3 style="margin: var(--lumo-space-s); padding: var(--lumo-space-s); align-self: flex-start;"> Objednejte si u nás kurz!</h3>
    <vaadin-form-item>
     <label slot="label">Jazyk:</label>
     <vaadin-select id="selectLanguages" style="width: 100%;"></vaadin-select>
    </vaadin-form-item>
    <vaadin-form-item>
     <label slot="label">Email:</label>
     <vaadin-text-field id="inputEmail" type="text" style="width: 100%;"></vaadin-text-field>
    </vaadin-form-item>
    <vaadin-form-item>
     <label slot="label">Jméno:</label>
     <vaadin-text-field type="text" id="inputName" style="width: 100%;"></vaadin-text-field>
    </vaadin-form-item>
    <vaadin-form-item>
     <label slot="label">Příjmení:</label>
     <vaadin-text-field type="text" id="inputSurname" style="width: 100%;"></vaadin-text-field>
    </vaadin-form-item>
    <vaadin-form-item>
     <label slot="label">Login:</label>
     <vaadin-text-field type="text" id="inputLogin" style="width: 100%;"></vaadin-text-field>
    </vaadin-form-item>
    <vaadin-form-item>
     <label slot="label">Heslo:</label>
     <vaadin-password-field id="inputPassword" type="password" style="width: 100%;"></vaadin-password-field>
    </vaadin-form-item>
   </vaadin-form-layout>
   <vaadin-button id="orderCourseButton" style="align-self: center; margin: var(--lumo-space-s); width: 100%;">
     Objednat kurz 
   </vaadin-button>
  </div>
 </div>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
