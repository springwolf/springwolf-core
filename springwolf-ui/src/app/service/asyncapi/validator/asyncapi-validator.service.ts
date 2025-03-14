/* SPDX-License-Identifier: Apache-2.0 */
import {Injectable} from "@angular/core";
import Ajv from "ajv";
import expectedSchema from "./server-async-api.schema.json";
import {ServerAsyncApi} from "../models/asyncapi.model";

@Injectable()
export class AsyncApiValidatorService {
    private readonly ajv = new Ajv({allErrors: true});

    public validate(item: ServerAsyncApi) {
        // configure strict mode
        this.ajv.opts.removeAdditional = false;
        const validateStrict = this.ajv.compile(expectedSchema)
        const isValidInStrictMode = validateStrict(item)
        if (!isValidInStrictMode) {
            console.warn("Validation error while parsing asyncapi file in Springwolf format (strict mode)", validateStrict.errors)
        }
        this.ajv.removeSchema(expectedSchema);

        // configure lenient mode
        this.ajv.opts.removeAdditional = true;

        const validateLenient = this.ajv.compile(expectedSchema)
        const isValidInLenientMode = validateLenient(item);
        if (!isValidInLenientMode) {
            console.warn(
                "Validation error while parsing asyncapi file in Springwolf format (lenient mode)",
                validateLenient.errors
            );
        }
    }
}
