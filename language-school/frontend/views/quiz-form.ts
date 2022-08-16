import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/progress-bar/src/vaadin-progress-bar.js';
import '@vaadin/button/src/vaadin-button.js';
import '@vaadin/grid/src/vaadin-grid.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@polymer/iron-icon/iron-icon.js';

@customElement('quiz-form')
export class QuizForm extends LitElement {
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
<vaadin-vertical-layout style="width: 100%; height: 100%; justify-content: flex-start;">
 <vaadin-progress-bar id="progressBar" style="margin: var(--lumo-space-xl); padding: var(--lumo-space-xl); align-self: center; width: 80%;"></vaadin-progress-bar>
 <vaadin-horizontal-layout theme="spacing" style="width: 100%; align-self: center; justify-content: center; align-items: center;">
  <vaadin-button id="buttonPrevious" style="flex-grow: 0;">
   <iron-icon icon="lumo:angle-left" slot="prefix"></iron-icon>
  </vaadin-button>
  <vaadin-text-field label="Otázka:" id="inputQuestion" style="width: 50%; align-self: center;" type="text"></vaadin-text-field>
  <vaadin-button id="buttonNext">
   <iron-icon icon="lumo:angle-right" slot="prefix"></iron-icon>
  </vaadin-button>
 </vaadin-horizontal-layout>
 <vaadin-horizontal-layout theme="spacing" style="width: 100%; flex-shrink: 1; align-self: center; justify-content: center; padding: var(--lumo-space-l);">
  <label id="labelQuestion" style="align-self: center;"></label>
  <vaadin-button id="buttonOption1">
    option1 
  </vaadin-button>
  <vaadin-button id="buttonOption2">
    option2 
  </vaadin-button>
  <vaadin-button id="buttonOption3">
    option3 
  </vaadin-button>
  <vaadin-button id="buttonOption4">
    option4 
  </vaadin-button>
 </vaadin-horizontal-layout>
 <vaadin-horizontal-layout theme="spacing" style="width: 100%; align-self: center; justify-content: center;">
  <label>Získaných bodů:</label>
  <label id="labelCorrect">0</label>
  <label>Špatných:</label>
  <label id="labelWrong">0</label>
 </vaadin-horizontal-layout>
 <h3 style="margin: var(--lumo-space-m);">Testy: </h3>
 <vaadin-grid id="gridUserTests" is-attached style="margin: var(--lumo-space-m); padding: var(--lumo-space-m);"></vaadin-grid>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
