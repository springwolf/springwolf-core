/* SPDX-License-Identifier: Apache-2.0 */
import {
  Component,
  ElementRef,
  ViewChild,
  AfterViewInit,
  input,
  model,
} from "@angular/core";
import { basicEditor } from "prism-code-editor/setups";
import { copyButton } from "prism-code-editor/copy-button";
import "prism-code-editor/prism/languages/json";
import "prism-code-editor/prism/languages/http";
import "prism-code-editor/prism/languages/java";
import "prism-code-editor/prism/languages/kotlin";
import "prism-code-editor/prism/languages/markdown";
import "prism-code-editor/themes/prism-okaidia.css";
import { PrismEditor } from "prism-code-editor";

@Component({
  selector: "app-prism-editor",
  template: "<div #editorContainer></div>",
  styles: [
    `
      :host > div {
        margin-bottom: 0.5em;
      }
    `,
  ],
})
export class PrismEditorComponent implements AfterViewInit {
  code = model<string>("");
  language = input<string>("markdown");
  readonly = input<boolean, string>(false, {
    transform: (value: string) => value == "true",
  });
  editor: PrismEditor | undefined = undefined;

  @ViewChild("editorContainer") editorContainer!: ElementRef;

  ngAfterViewInit(): void {
    if (this.editorContainer.nativeElement) {
      this.editor = basicEditor(this.editorContainer.nativeElement, {
        value: this.code(),
        language: this.language(),
        theme: "prism-okaidia",
        lineNumbers: false,
        wordWrap: true,
        readOnly: this.readonly(),

        onUpdate: (code: string) => {
          this.code.set(code);
        },
      });
      this.editor.addExtensions(copyButton());

      this.code.subscribe((code) => {
        this.editor?.update();
      });
    }
  }
}
