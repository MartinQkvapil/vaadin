import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/button/src/vaadin-button.js';
import '@vaadin/grid/src/vaadin-grid.js';
import '@vaadin/vaadin-form-layout/vaadin-form-layout.js';
import '@vaadin/text-area/src/vaadin-text-area.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/select/src/vaadin-select.js';
import '@vaadin/list-box/src/vaadin-list-box.js';
import '@vaadin/item/src/vaadin-item.js';
import '@vaadin/vaadin-form-layout/vaadin-form-item.js';

@customElement('question-form')
export class QuestionForm extends LitElement {
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
<vaadin-vertical-layout theme="spacing" style="width: 100%; height: 100%; margin: var(--lumo-space-xs); padding: var(--lumo-space-xs);">
 <div style="width: 90%; align-self: center; margin: var(--lumo-space-s); padding: var(--lumo-space-s);">
  <vaadin-form-layout style="width: 100%;">
   <vaadin-form-item>
    <label slot="label">Bodů: </label>
    <vaadin-select value="Item one" id="selectPoints">
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
   </vaadin-form-item>
   <vaadin-form-item>
    <label slot="label">Otázka: </label>
    <vaadin-text-area id="textAreaQuestion"></vaadin-text-area>
   </vaadin-form-item>
   <vaadin-form-item>
    <label slot="label">Správná odpověď: </label>
    <vaadin-text-field type="text" id="inputCorrectAnswer"></vaadin-text-field>
   </vaadin-form-item>
   <vaadin-form-item>
    <label slot="label">Špatná-1 :</label>
    <vaadin-text-field type="text" id="inputWrong1"></vaadin-text-field>
   </vaadin-form-item>
   <vaadin-form-item>
    <label slot="label">Špatná-2 :</label>
    <vaadin-text-field type="text" id="inputWrong2"></vaadin-text-field>
   </vaadin-form-item>
   <vaadin-form-item>
    <label slot="label">Špatná-3:</label>
    <vaadin-text-field type="text" id="inputWrong3"></vaadin-text-field>
   </vaadin-form-item>
  </vaadin-form-layout>
 </div>
 <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
  <vaadin-button id="buttonSave">
    Uložit 
  </vaadin-button>
  <vaadin-button id="buttonEdit">
    Editovat 
  </vaadin-button>
  <vaadin-button id="buttonRemove">
    Smazat 
  </vaadin-button>
  <vaadin-button id="buttonClear">
    Reset 
  </vaadin-button>
 </vaadin-horizontal-layout>
 <vaadin-grid id="gridQuestions" is-attached style="width: 90%; align-self: center; margin: var(--lumo-space-s); padding: var(--lumo-space-s);"></vaadin-grid>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
