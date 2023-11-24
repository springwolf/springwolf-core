<h1 align="center">
<img src="logo.png" alt="Logo" width="100"/> Springwolf
</h1>

<p align="center">
<strong>Automated documentation for event-driven applications built with Spring Boot</strong>
</p>

![Last Version](https://img.shields.io/github/tag-pre/springwolf/springwolf-core.svg)
![GitHub commits since latest release (by SemVer including pre-releases)](https://img.shields.io/github/commits-since/springwolf/springwolf-core/latest)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

[![springwolf-core](https://github.com/springwolf/springwolf-core/actions/workflows/springwolf-core.yml/badge.svg)](https://github.com/springwolf/springwolf-core/actions/workflows/springwolf-core.yml)
[![springwolf-asyncapi](https://github.com/springwolf/springwolf-core/actions/workflows/springwolf-asyncapi.yml/badge.svg)](https://github.com/springwolf/springwolf-core/actions/workflows/springwolf-asyncapi.yml)
[![springwolf-ui](https://github.com/springwolf/springwolf-core/actions/workflows/springwolf-ui.yml/badge.svg)](https://github.com/springwolf/springwolf-core/actions/workflows/springwolf-ui.yml)
[![springwolf-plugins](https://github.com/springwolf/springwolf-core/actions/workflows/springwolf-plugins.yml/badge.svg)](https://github.com/springwolf/springwolf-core/actions/workflows/springwolf-plugins.yml)
[![springwolf-addons](https://github.com/springwolf/springwolf-core/actions/workflows/springwolf-addons.yml/badge.svg)](https://github.com/springwolf/springwolf-core/actions/workflows/springwolf-addons.yml)
> We are on discord for any question, discussion, request etc.
> Join us at https://discord.gg/HZYqd5RPTd

### Contents

- [About](#about)
- [Demo & Documentation](#-demo---documentation)
- [Why You Should Use Springwolf](#-why-you-should-use-springwolf)
- [Usage & Example](#-usage--example)
- [Who's Using Springwolf](#-whos-using-springwolf)
- [How To Participate](#-how-to-participate)
- [Contributors](#-contributors)

### About

This project is inspired by [Springfox](https://github.com/springfox/springfox).
It documents asynchronous APIs using the [AsyncAPI specification](https://www.asyncapi.com/).

`springwolf-ui` adds a web UI, much like that of Springfox, and allows easy publishing of auto-generated payload examples.

### 🪇 Demo & 📖 Documentation

Take a look at the Springwolf [live demo](https://demo.springwolf.dev/) and a [generated AsyncAPI document](springwolf-examples/springwolf-kafka-example/src/test/resources/asyncapi.json).

[springwolf.dev](https://www.springwolf.dev) includes the [quickstart guide](https://www.springwolf.dev/docs/quickstart) and full documentation.

### ✨ Why You Should Use Springwolf

Springwolf exploits the fact that you already fully described your consumer endpoint (with listener annotations, such as
`@KafkaListener`, `@RabbitListener`, `@SqsListener`, etc.) and generates the documentation based on this information.

<details><summary>Share API Schema Definition</summary>

The AsyncAPI conform documentation can be integrated into API hubs (like [backstage](https://backstage.io/docs/features/software-catalog/descriptor-format/))
or be shared as a `json`/`yaml` file with others.
</details>

<details><summary>UI Based API Testing</summary>

In projects using asynchronous APIs, you may often find yourself needing to manually send a message to some topic,
whether you are manually testing a new feature, debugging or trying to understand some flow.

Using the automatically generated example payload object as a suggestion, you can publish it to the correct channel with a single click.
</details>

### 🔬 Usage & Example

Protocols not supported natively can still be documented using `@AsyncListener` and `@AsyncPublisher` annotation.
More details in the documentation.

| Code / Plugin / Addon                                                                                                                     | Example project                                                                                                                       | Current version                                                                                                                                                                     | SNAPSHOT version                                                                                                                                                                                                       |
|-------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Core](https://github.com/springwolf/springwolf-core/tree/master/springwolf-core)                                                         |                                                                                                                                       | ![Maven Central](https://img.shields.io/maven-central/v/io.github.springwolf/springwolf-core?color=green&label=springwolf-core&style=plastic)                                       | ![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/io.github.springwolf/springwolf-core?label=springwolf-core&server=https%3A%2F%2Fs01.oss.sonatype.org&style=plastic)                                       |
| [AMQP](https://github.com/springwolf/springwolf-core/tree/master/springwolf-plugins/springwolf-amqp-plugin)                               | [AMQP Example](https://github.com/springwolf/springwolf-core/tree/master/springwolf-examples/springwolf-amqp-example)                 | ![Maven Central](https://img.shields.io/maven-central/v/io.github.springwolf/springwolf-amqp?color=green&label=springwolf-amqp&style=plastic)                                       | ![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/io.github.springwolf/springwolf-amqp?label=springwolf-amqp&server=https%3A%2F%2Fs01.oss.sonatype.org&style=plastic)                                       |
| [AWS SNS](https://github.com/springwolf/springwolf-core/tree/master/springwolf-plugins/springwolf-sns-plugin)                             | [AWS SNS Example](https://github.com/springwolf/springwolf-core/tree/master/springwolf-examples/springwolf-sns-example)               | ![Maven Central](https://img.shields.io/maven-central/v/io.github.springwolf/springwolf-sns?color=green&label=springwolf-sns&style=plastic)                                         | ![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/io.github.springwolf/springwolf-sns?label=springwolf-sns&server=https%3A%2F%2Fs01.oss.sonatype.org&style=plastic)                                         |
| [AWS SQS](https://github.com/springwolf/springwolf-core/tree/master/springwolf-plugins/springwolf-sqs-plugin)                             | [AWS SQS Example](https://github.com/springwolf/springwolf-core/tree/master/springwolf-examples/springwolf-sqs-example)               | ![Maven Central](https://img.shields.io/maven-central/v/io.github.springwolf/springwolf-sqs?color=green&label=springwolf-sqs&style=plastic)                                         | ![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/io.github.springwolf/springwolf-sqs?label=springwolf-sqs&server=https%3A%2F%2Fs01.oss.sonatype.org&style=plastic)                                         |
| [Cloud Stream](https://github.com/springwolf/springwolf-core/tree/master/springwolf-plugins/springwolf-cloud-stream-plugin)               | [Cloud Stream Example](https://github.com/springwolf/springwolf-core/tree/master/springwolf-examples/springwolf-cloud-stream-example) | ![Maven Central](https://img.shields.io/maven-central/v/io.github.springwolf/springwolf-cloud-stream?color=green&label=springwolf-cloud-stream&style=plastic)                       | ![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/io.github.springwolf/springwolf-cloud-stream?label=springwolf-cloud-stream&server=https%3A%2F%2Fs01.oss.sonatype.org&style=plastic)                       |
| [JMS](https://github.com/springwolf/springwolf-core/tree/master/springwolf-plugins/springwolf-jms-plugin)                                 | [JMS Example](https://github.com/springwolf/springwolf-core/tree/master/springwolf-examples/springwolf-jms-example)                   | ![Maven Central](https://img.shields.io/maven-central/v/io.github.springwolf/springwolf-jms?color=green&label=springwolf-jms&style=plastic)                                         | ![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/io.github.springwolf/springwolf-jms?label=springwolf-jms&server=https%3A%2F%2Fs01.oss.sonatype.org&style=plastic)                                         |
| [Kafka](https://github.com/springwolf/springwolf-core/tree/master/springwolf-plugins/springwolf-kafka-plugin)                             | [Kafka Example](https://github.com/springwolf/springwolf-core/tree/master/springwolf-examples/springwolf-kafka-example)               | ![Maven Central](https://img.shields.io/maven-central/v/io.github.springwolf/springwolf-kafka?color=green&label=springwolf-kafka&style=plastic)                                     | ![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/io.github.springwolf/springwolf-kafka?label=springwolf-kafka&server=https%3A%2F%2Fs01.oss.sonatype.org&style=plastic)                                     |
| [Common Model Converter](https://github.com/springwolf/springwolf-core/tree/master/springwolf-add-ons/springwolf-common-model-converters) |                                                                                                                                       | ![Maven Central](https://img.shields.io/maven-central/v/io.github.springwolf/springwolf-common-model-converters?color=green&label=springwolf-common-model-converters&style=plastic) | ![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/io.github.springwolf/springwolf-common-model-converters?label=springwolf-common-model-converters&server=https%3A%2F%2Fs01.oss.sonatype.org&style=plastic) |
| [Generic Binding](https://github.com/springwolf/springwolf-core/tree/master/springwolf-add-ons/springwolf-generic-binding)                |                                                                                                                                       | ![Maven Central](https://img.shields.io/maven-central/v/io.github.springwolf/springwolf-generic-binding?color=green&label=springwolf-generic-binding&style=plastic)                 | ![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/io.github.springwolf/springwolf-generic-binding?label=springwolf-generic-binding&server=https%3A%2F%2Fs01.oss.sonatype.org&style=plastic)                 |
| [Json Schema](https://github.com/springwolf/springwolf-core/tree/master/springwolf-add-ons/springwolf-json-schema)                        |                                                                                                                                       | ![Maven Central](https://img.shields.io/maven-central/v/io.github.springwolf/springwolf-json-schema?color=green&label=springwolf-json-schema&style=plastic)                         | ![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/io.github.springwolf/springwolf-json-schema?label=springwolf-json-schema&server=https%3A%2F%2Fs01.oss.sonatype.org&style=plastic)                         |

### 🚀 Who's Using Springwolf

- [b.well Connected Health](https://www.icanbwell.com/)
- [LVM Versicherung](https://www.lvm.de/privatkunden/)
- [OTTO](https://www.otto.de)
- [Teambank](https://www.teambank.de)
- [2bPrecise](https://2bprecisehealth.com/)

Comment in [this PR](https://github.com/springwolf/springwolf-core/issues/342) to add your company and spread the word

### ✏️ How To Participate

Check out our [CONTRIBUTING.md](CONTRIBUTING.md) guide.

<details><summary>Testing SNAPSHOT version</summary>

#### Sonatype snapshots

Add the following to the `repositories` closure in `build.gradle`:

```groovy
repositories {
    // ...
    maven {
        url "https://s01.oss.sonatype.org/content/repositories/snapshots"
    }
}
```

Or add the `repository` to your `pom.xml` if you are using maven:

```xml

<repositories>
    <repository>
        <id>oss-sonatype</id>
        <name>oss-sonatype</name>
        <url>https://s01.oss.sonatype.org/content/repositories/snapshots</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
 ```

#### Local Snapshot Build

To work with local builds, run the `publishToMavenLocal` task. The current version number is set in [`.env`](.env) file.
</details>

### 👏  Contributors

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tbody>
    <tr>
      <td align="center" valign="top" width="14.28%"><a href="https://stavshamir.github.io/"><img src="https://avatars.githubusercontent.com/u/22257261?v=4?s=100" width="100px;" alt="Stav Shamir"/><br /><sub><b>Stav Shamir</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=stavshamir" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/timonback"><img src="https://avatars.githubusercontent.com/u/7568775?v=4?s=100" width="100px;" alt="Timon Back"/><br /><sub><b>Timon Back</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=timonback" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/sam0r040"><img src="https://avatars.githubusercontent.com/u/93372330?v=4?s=100" width="100px;" alt="sam0r040"/><br /><sub><b>sam0r040</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=sam0r040" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/ctasada"><img src="https://avatars.githubusercontent.com/u/1381772?v=4?s=100" width="100px;" alt="Carlos Tasada"/><br /><sub><b>Carlos Tasada</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=ctasada" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/jrlambs"><img src="https://avatars.githubusercontent.com/u/13246477?v=4?s=100" width="100px;" alt="jrlambs"/><br /><sub><b>jrlambs</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=jrlambs" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/DmitriButorchin"><img src="https://avatars.githubusercontent.com/u/54904872?v=4?s=100" width="100px;" alt="DmitriButorchin"/><br /><sub><b>DmitriButorchin</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=DmitriButorchin" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/tvahrst"><img src="https://avatars.githubusercontent.com/u/1325409?v=4?s=100" width="100px;" alt="Thomas Vahrst"/><br /><sub><b>Thomas Vahrst</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=tvahrst" title="Code">💻</a></td>
    </tr>
    <tr>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/yasen-pavlov"><img src="https://avatars.githubusercontent.com/u/91540875?v=4?s=100" width="100px;" alt="Yasen Pavlov"/><br /><sub><b>Yasen Pavlov</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=yasen-pavlov" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://www.ageweiler.de/"><img src="https://avatars.githubusercontent.com/u/4521930?v=4?s=100" width="100px;" alt="Arthur Geweiler"/><br /><sub><b>Arthur Geweiler</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=harare" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/CS-BASF"><img src="https://avatars.githubusercontent.com/u/137758458?v=4?s=100" width="100px;" alt="CS-BASF"/><br /><sub><b>CS-BASF</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=CS-BASF" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://vanwilgenburg.wordpress.com/"><img src="https://avatars.githubusercontent.com/u/251901?v=4?s=100" width="100px;" alt="Jeroen van Wilgenburg"/><br /><sub><b>Jeroen van Wilgenburg</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=jvwilge" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/strelchm"><img src="https://avatars.githubusercontent.com/u/23243577?v=4?s=100" width="100px;" alt="Michael Strelchenko"/><br /><sub><b>Michael Strelchenko</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=strelchm" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/ogaudefroy"><img src="https://avatars.githubusercontent.com/u/959653?v=4?s=100" width="100px;" alt="Olivier Gaudefroy"/><br /><sub><b>Olivier Gaudefroy</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=ogaudefroy" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/Omerbea"><img src="https://avatars.githubusercontent.com/u/7481612?v=4?s=100" width="100px;" alt="Omerbea"/><br /><sub><b>Omerbea</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=Omerbea" title="Code">💻</a></td>
    </tr>
    <tr>
      <td align="center" valign="top" width="14.28%"><a href="https://www.linkedin.com/in/pavel-bo/"><img src="https://avatars.githubusercontent.com/u/3388414?v=4?s=100" width="100px;" alt="Pavel Bodiachevskii"/><br /><sub><b>Pavel Bodiachevskii</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=Pakisan" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/sergiorc"><img src="https://avatars.githubusercontent.com/u/3658801?v=4?s=100" width="100px;" alt="Sergio Roldán"/><br /><sub><b>Sergio Roldán</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=sergiorc" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/Stmated"><img src="https://avatars.githubusercontent.com/u/12374471?v=4?s=100" width="100px;" alt="Stmated"/><br /><sub><b>Stmated</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=Stmated" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/themis-pyrgiotis"><img src="https://avatars.githubusercontent.com/u/1893315?v=4?s=100" width="100px;" alt="Themistoklis Pyrgiotis"/><br /><sub><b>Themistoklis Pyrgiotis</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=themis-pyrgiotis" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/ZachHubbs"><img src="https://avatars.githubusercontent.com/u/6667523?v=4?s=100" width="100px;" alt="Zach Hubbs"/><br /><sub><b>Zach Hubbs</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=ZachHubbs" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/biergit"><img src="https://avatars.githubusercontent.com/u/1071968?v=4?s=100" width="100px;" alt="biergit"/><br /><sub><b>biergit</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=biergit" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/kalarani"><img src="https://avatars.githubusercontent.com/u/318466?v=4?s=100" width="100px;" alt="kalarani"/><br /><sub><b>kalarani</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=kalarani" title="Code">💻</a></td>
    </tr>
    <tr>
      <td align="center" valign="top" width="14.28%"><a href="https://dipeshsingh253.github.io/"><img src="https://avatars.githubusercontent.com/u/84814627?v=4?s=100" width="100px;" alt="Dipesh Singh"/><br /><sub><b>Dipesh Singh</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=dipeshsingh253" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/Sakshi-75"><img src="https://avatars.githubusercontent.com/u/20265098?v=4?s=100" width="100px;" alt="Sakshi Jain"/><br /><sub><b>Sakshi Jain</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=Sakshi-75" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/SheheryarAamir"><img src="https://avatars.githubusercontent.com/u/684528?v=4?s=100" width="100px;" alt="Sheheryar Aamir"/><br /><sub><b>Sheheryar Aamir</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=SheheryarAamir" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/jmwestbe"><img src="https://avatars.githubusercontent.com/u/26258285?v=4?s=100" width="100px;" alt="jmwestbe"/><br /><sub><b>jmwestbe</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=jmwestbe" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/pdalfarr"><img src="https://avatars.githubusercontent.com/u/1537201?v=4?s=100" width="100px;" alt="pdalfarr"/><br /><sub><b>pdalfarr</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=pdalfarr" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/krzysztofxkwiecien"><img src="https://avatars.githubusercontent.com/u/37042650?v=4?s=100" width="100px;" alt="Krzysztof Kwiecień"/><br /><sub><b>Krzysztof Kwiecień</b></sub></a><br /><a href="https://github.com/stavshamir/Springwolf/commits?author=krzysztofxkwiecien" title="Code">💻</a></td>
    </tr>
  </tbody>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

To add yourself as a contributor, install the [all-contributors CLI](https://allcontributors.org/docs/en/cli/installation) and run:
1. `all-contributors check` 
2. `all-contributors add <username> code`
3. `all-contributors generate`
