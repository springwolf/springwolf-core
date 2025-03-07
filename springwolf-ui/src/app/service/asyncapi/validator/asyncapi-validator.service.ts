/* SPDX-License-Identifier: Apache-2.0 */
import { Injectable } from "@angular/core";
import Ajv from "ajv";
import expectedSchema from "./server-async-api.schema.json";
import { ServerAsyncApi } from "../models/asyncapi.model";

@Injectable()
export class AsyncApiValidatorService {
  // private readonly ajv = new Ajv({allErrors: true, removeAdditional: true});
  private readonly ajvInStrictMode = new Ajv({
    allErrors: true,
    removeAdditional: false,
  });

  public validate(item: ServerAsyncApi) {
    // const validate = this.ajv.compile(expectedSchema);
    // const isValid = validate(item)
    // if(!isValid) {
    // console.warn("Validation error while parsing asyncapi file in Springwolf format", validate.errors)
    // }

    const validateStrict = this.ajvInStrictMode.compile(expectedSchema);
    const isValidInStrictMode = validateStrict(item);
    if (!isValidInStrictMode) {
      console.info(
        "Validation error while parsing asyncapi file in Springwolf format (strict mode)",
        validateStrict.errors
      );
    }
  }
}
