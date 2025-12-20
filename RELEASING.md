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
   7. STOMP (WebSocket): https://stomp.demo.springwolf.dev/
3. Update `all-contributors` in [README.md](README.md#-contributors)
4. Create a new branch `release/1.<minor-version-number>.X`, remove the `-SNAPHSOT` postfix in `.env`, commit & push
5. Run GitHub `Publish releases` pipeline from the newly created release branch
6. Update the version number in `.env` for next snapshot on branch `main`, commit & push
7. Update version number on website
8. Publish the release notes on GitHub (https://github.com/springwolf/springwolf-core/releases)

## GPG Key for signing the release artifacts

A valid and published GPG key is required for publishing to Sonatype maven.

> The current key can be obtained via:
> `gpg --keyserver keyserver.ubuntu.com --search-keys 5A86573F7588809B50EB8EF475ABBF11907B8027`

As documented in [gradle signing plugin](https://docs.gradle.org/current/userguide/signing_plugin.html),
`gpg` is used for artifact signing.

Steps to create a new key:
1. Generate a new GPG key: `gpg --full-generate-key`
2. Get key id: `gpg --list-secret-keys --keyid-format LONG`
3. Export key for backup in secure storage:
    - Private key: `gpg --armor --export-secret-key 75ABBF11907B8027 > springwolf.gpg.key`
    - Public key: `gpg --armor --export 75ABBF11907B8027 > springwolf.gpg.pub`
    - Revocation cert: `gpg --output springwolf.gpg.revoke.asc --gen-revoke 5A86573F7588809B50EB8EF475ABBF11907B8027`
    - And your key password
4. Upload key: `gpg --keyserver keyserver.ubuntu.com --send-keys 5A86573F7588809B50EB8EF475ABBF11907B8027`
5. Verify key was uploaded (after a couple seconds): `gpg --keyserver keyserver.ubuntu.com --search-keys 5A86573F7588809B50EB8EF475ABBF11907B8027`
6. Add secret to GitHub:
    - `SIGNINGKEY` is the content of `springwolf.gpg.key`
    - `SIGNINGPASSWORD` is the password used to generate the key
