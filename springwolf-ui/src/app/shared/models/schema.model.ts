import { Example } from './example.model';

export interface Schema {
    description?: String;
    type: String;
    properties?: Map<string, Schema>;
    example: Example;
    required?: string[];
}
