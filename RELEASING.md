# Releasing

The following list describe the steps necessary to release a new version.

1. Run all tests (including all examples + integration)
2. Run docker compose and manually test ui (or verify on website):
   1. AMQP: https://amqp.demo.springwolf.dev/
   2. CloudStream https://cloud-stream.demo.springwolf.dev/
   3. JMS: https://jms.demo.springwolf.dev/
   4. Kafka: https://kafka.demo.springwolf.dev/
   5. SNS: https://sns.demo.springwolf.dev/
   6. SQS: https://sqs.demo.springwolf.dev/
3. Update `all-contributors` in [README.md](README.md)
4. Remove the `-SNAPHSOT` postfix in `.env`, create a new branch `release/0.X.X` (version number), commit & push
5. Run GitHub `Publish releases` pipeline from the newly created release branch
   1. The stage artifacts can be viewed in https://s01.oss.sonatype.org/#view-repositories
   2. To view them, select `Nexus Managed Repositories` in the drop-down and search for springwolf in the searchbox 
6. Release version in nexus
   1. Artifacts are visible in the public maven repository: https://repo1.maven.org/maven2/io/github/springwolf/
7. Update version number on website
8. Update the version number in `.env` for next snapshot on branch `master`, commit & push
