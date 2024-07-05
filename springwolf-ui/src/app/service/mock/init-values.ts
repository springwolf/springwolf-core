/* SPDX-License-Identifier: Apache-2.0 */
import { Example } from "../../models/example.model";
import { Schema } from "../../models/schema.model";
import { Operation } from "../../models/operation.model";
import { Binding, Bindings } from "../../models/bindings.model";
import { Message } from "../../models/message.model";
import { Info } from "../../models/info.model";

export const initExample = new Example("init");
export const initBindings: Bindings = {};

export const initInfo: Info = {
  asyncApiJson: {},
  contact: {},
  license: {},
  title: "",
  version: "",
};

export const initMessage: Message = {
  bindings: new Map<string, Binding>(),
  headers: { anchorUrl: "", name: "", title: "" },
  name: "",
  payload: { anchorUrl: "", name: "", title: "", type: "" },
  rawBindings: {},
  title: "",
};
export const initOperation: Operation = {
  bindings: initBindings,
  message: initMessage,
  operationType: "send",
  protocol: "",
};

export const initSchema: Schema = {
  title: "",
};
