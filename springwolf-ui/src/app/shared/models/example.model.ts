export class Example {

  public value: string;
  public lineCount: number;

  constructor(exampleObject: object | string) {
    if (typeof exampleObject === 'string') {
      this.value = exampleObject;
    } else {
      this.value = JSON.stringify(exampleObject, null, 2);
    }

    this.lineCount = this.value.split('\n').length;
  }

}
