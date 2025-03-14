/* SPDX-License-Identifier: Apache-2.0 */
import { Injectable } from "@angular/core";
import Ajv from "ajv";
import expectedSchema from "./server-async-api.schema.json";
import { ServerAsyncApi } from "../models/asyncapi.model";
import { INotificationService } from "../../notification.service";

@Injectable()
export class AsyncApiValidatorService {
  private readonly ajv = new Ajv({ allErrors: true });
  public _logToConsole = true;

  constructor(private notificationService: INotificationService) {}

  public validate(item: ServerAsyncApi) {
    // configure strict mode
    this.ajv.opts.removeAdditional = false;
    const validateStrict = this.ajv.compile(expectedSchema);
    const isValidInStrictMode = validateStrict(item);
    if (!isValidInStrictMode) {
      this._logToConsole &&
        console.info(
          "Validation error while parsing AsyncAPI file in Springwolf format (strict mode)",
          validateStrict.errors
        );
    }
    this.ajv.removeSchema(expectedSchema);

    // configure lenient mode
    this.ajv.opts.removeAdditional = true;

    const validateLenient = this.ajv.compile(expectedSchema);
    const isValidInLenientMode = validateLenient(item);
    if (!isValidInLenientMode) {
      this.notificationService.showError(
        "Validation error while parsing AsyncAPI file in Springwolf format (lenient mode), see console logs for details."
      );

      this._logToConsole &&
        console.warn(
          "Validation error while parsing AsyncAPI file in Springwolf format (lenient mode)",
          validateLenient.errors
        );
    }
  }
}
