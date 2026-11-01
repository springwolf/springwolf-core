/* SPDX-License-Identifier: Apache-2.0 */
import { Example } from "../../models/example.model";
import { Schema, SchemaRef } from "../../models/schema.model";
import { Operation } from "../../models/operation.model";
import { Binding, Bindings } from "../../models/bindings.model";
import { Message } from "../../models/message.model";
import { Info } from "../../models/info.model";

// These default values initialize Angular signals/components before async API data is loaded.
// They provide an empty, valid state while the real model is being fetched.
export const initExample = new Example("init");
export const noExample = new Example("");
export const initBindings: Bindings = {};

export const initInfo: Info = {
  asyncApiJson: {},
  contact: {},
  license: {},
  title: "",
  version: "",
};

export const initSchemaRef: SchemaRef = {
  ts_type: "ref",
  anchorUrl: "",
  name: "",
  title: "",
};

export const initMessage: Message = {
  bindings: new Map<string, Binding>(),
  headers: initSchemaRef,
  name: "",
  payload: initSchemaRef,
  rawBindings: {},
  title: "",
  contentType: "",
};
export const initOperation: Operation = {
  channelName: "",
  bindings: initBindings,
  message: initMessage,
  operationType: "send",
  protocol: "",
  servers: [],
};

export const initSchema: Schema = {
  ts_type: "object",

  title: "",
  name: "",
  anchorUrl: "",
  anchorIdentifier: "",
  usedBy: [],
};
