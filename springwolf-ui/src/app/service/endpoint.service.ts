/* SPDX-License-Identifier: Apache-2.0 */
export class EndpointService {
  private static contextPath = EndpointService.getContextPath();

  private static getContextPath(): string {
    let url = document.location.pathname;
    return url.split("/asyncapi-ui.html")[0];
  }

  public static docs = EndpointService.contextPath + "/docs";

  public static getPublishEndpoint(protocol: string): string {
    return EndpointService.contextPath + `/${protocol}/publish`;
  }
}
