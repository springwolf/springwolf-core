export class KafkaEndpoint {
  constructor(
    public topic: string,
    public payloadClassName: string,
    public payloadModelName: string,
    public payloadExample: object
  ) { }
}
