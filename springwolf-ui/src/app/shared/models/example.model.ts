export class Example {

  public value: string;
  public lineCount: number;

  constructor(exampleObject: object) {
    this.value = JSON.stringify(exampleObject, null, 2);
    this.lineCount = this.value.split('\n').length;
  }

}