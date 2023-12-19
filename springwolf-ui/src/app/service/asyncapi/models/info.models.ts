export interface ServerAsyncApiInfo {
  title: string;
  version: string;
  description?: string;
  // TODO: use license
  license?: {
    name?: string;
    url?: string;
  }
}
