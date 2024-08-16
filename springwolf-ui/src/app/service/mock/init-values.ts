/* SPDX-License-Identifier: Apache-2.0 */
import { Example } from "../../models/example.model";
import { Schema } from "../../models/schema.model";
import { Operation } from "../../models/operation.model";
import { Binding, Bindings } from "../../models/bindings.model";
import { Message } from "../../models/message.model";
import { Info } from "../../models/info.model";

// TODO: are the init values needed?
export const initExample = new Example("init");
export const noExample = new Example("no-example-found");
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
  payload: { anchorUrl: "", name: "", title: "" },
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
  title: "",
  name: "",
  anchorUrl: "",
  anchorIdentifier: "",
  usedBy: [],
};
