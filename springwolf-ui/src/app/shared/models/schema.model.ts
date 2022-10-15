import { Example } from './example.model';

export interface Schema {
    description?: string;
    type: string;
    format?: string;
    enum?: string[];
    properties?: Map<string, Schema>;
    example?: Example;
    required?: string[];
}
