export class Endpoints {

    private static contextPath = Endpoints.getContextPath();

    private static getContextPath(): string {
        let url = document.location.pathname;
        return url.split("/asyncapi-ui.html")[0];
    }

    public static docs = Endpoints.contextPath + '/docs';

    public static getPublishEndpoint(protocol: string): string {
        return Endpoints.contextPath + `/${protocol}/publish`;
    }

}

