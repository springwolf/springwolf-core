import { Example } from './example.model';

export interface Schema {
    description?: string;
    anchorIdentifier: string;
    type: string;
    format?: string;
    enum?: string[];
    properties?: Map<string, Schema>;
    example?: Example;
    required?: string[];
}
