/* SPDX-License-Identifier: Apache-2.0 */
import { createEditor } from "prism-code-editor";

/**
 * Used by ng-markdown
 *
 * This bridges the call to the prism-code-editor implementation
 */
declare let Prism: {
  highlightAllUnder: (element: Element | Document) => void;
};

(globalThis as any).Prism = {
  highlightAllUnder: (element: Element | Document) => {
    element.querySelectorAll("pre code").forEach((block) => {
      const language =
        Array.from(block.classList).find((cls) => cls.startsWith("language")) ||
        "";
      const textContent = block.textContent || "";
      block.textContent = ""; // overwrite the content with the editor

      createEditor(block.parentElement, {
        value: textContent.trimEnd(),
        language: language.replace("language-", ""),
        lineNumbers: false,
        wordWrap: true,
        readOnly: true,
      });
    });
  },
};
