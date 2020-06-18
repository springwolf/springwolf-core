import { Example } from './example.model';

export interface Schema {
    type: String;
    properties: object;
    example: Example;
}