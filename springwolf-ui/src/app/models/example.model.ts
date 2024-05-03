/* SPDX-License-Identifier: Apache-2.0 */
export class Example {
  public value: string;
  public rawValue: object | string;
  public lineCount: number;

  constructor(exampleObject: object | string) {
    this.rawValue = exampleObject;

    if (typeof exampleObject === "string") {
      this.value = exampleObject;
    } else if (Object.keys(exampleObject).length === 0) {
      this.value = "";
    } else {
      this.value = JSON.stringify(exampleObject, null, 2);
    }

    this.lineCount = this.value.split("\n").length;
  }
}
