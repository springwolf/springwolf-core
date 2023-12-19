export interface ServerServers {
  [server: string]: {
    host: string;
    protocol: string;
    description?: string;
  };
}
