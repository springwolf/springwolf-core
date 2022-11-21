import { Example } from './example.model';

export interface Schema {
    name?: string;
    description?: string;

    anchorIdentifier?: string;
    anchorUrl?: string;

    type?: string;
    // type == object
    properties?: Map<string, Schema>;
    // type == array
    items?: Schema;

    format?: string;
    required?: string[];
    enum?: string[];
    example?: Example;
}
