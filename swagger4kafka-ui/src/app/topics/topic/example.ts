export class Example {

  public value: string;
  public lineCount: number;

  constructor(exampleObject: object) {
    this.value = JSON.stringify(exampleObject, null, 2);
    this.lineCount = 1 + this.value.split('\n').length;
  }

}
