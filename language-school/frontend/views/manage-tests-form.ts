import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/split-layout/src/vaadin-split-layout.js';
import '@vaadin/grid/src/vaadin-grid.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/select/src/vaadin-select.js';
import '@vaadin/list-box/src/vaadin-list-box.js';
import '@vaadin/item/src/vaadin-item.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/button/src/vaadin-button.js';

@customElement('manage-tests-form')
export class ManageTestsForm extends LitElement {
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
<vaadin-vertical-layout style="width: 100%; height: 100%;" theme="spacing-s">
 <div style="width: 90%; flex-grow: 1; align-self: center; margin: var(--lumo-space-s); padding: var(--lumo-space-s);">
  <vaadin-split-layout style="width: 100%;">
   <vaadin-vertical-layout theme="spacing">
    <vaadin-grid id="gridTests" is-attached></vaadin-grid>
    <vaadin-horizontal-layout theme="spacing-s" style="width: 100%; align-self: center; justify-content: center;">
     <vaadin-text-field id="inputTestName" type="text"></vaadin-text-field>
     <vaadin-select value="Item one" id="selectLanguage">
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
     <vaadin-button id="buttonCreateTest" style="flex-grow: 0; flex-shrink: 0;">
       Vytvořit 
     </vaadin-button>
     <vaadin-button theme="primary error" id="buttonRemoveTest">
       Odebrat 
     </vaadin-button>
    </vaadin-horizontal-layout>
   </vaadin-vertical-layout>
   <vaadin-vertical-layout theme="spacing">
    <vaadin-grid id="gridQuestions" is-attached></vaadin-grid>
    <vaadin-horizontal-layout theme="spacing" style="justify-content: center; align-self: center;">
     <vaadin-select value="Item one" id="selectQuestion">
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
      </template>Question 
     </vaadin-select>
     <vaadin-button id="buttonAddQuestion">
       Přidat 
     </vaadin-button>
     <vaadin-button theme="primary error" id="buttonRemoveQuestion">
       Odebrat 
     </vaadin-button>
    </vaadin-horizontal-layout>
   </vaadin-vertical-layout>
  </vaadin-split-layout>
  <vaadin-horizontal-layout theme="spacing" style="width: 100%;"></vaadin-horizontal-layout>
 </div>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
