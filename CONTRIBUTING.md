# Contributing to springwolf

First off, thanks for taking the time to contribute! ❤️

All types of contributions are encouraged and valued.
Here, we collected some advice to smooth out the experience for everyone involved.

**Table of content**
- [I Have A Question](#i-have-a-question)
- [Reporting a bug](#reporting-a-bug)
- [Contributing code](#contributing-code)

> And if you like the project, but just don't have time to contribute, that's fine. Publicity in the form of github star, a tweet, mentioning in your project's readme and/or telling a friend (at a meetup) help as well.

## I Have A Question

> Some questions can be answered by reading our [documentation](https://www.springwolf.dev/docs/faq) including the FAQ page.

Before you ask a question, it is best to search for existing [Issues](https://github.com/springwolf/springwolf-core/issues) that might help you.
In case you have found a suitable issue and still need clarification, you can write your question in this issue.

If you then still feel the need to ask a question and need clarification, we recommend the following:
- Open an [issues](https://github.com/springwolf/springwolf-core/issues)
- Connect with us on discord (see [README](README.md))

## Reporting a bug

Head over to our [issue](https://github.com/springwolf/springwolf-core/issues?q=is%3Aissue) page and file an issue.

Please search also the closed issues whether your question has been answered before.

## Contributing code
> **Legal Notice**
> 
> When contributing to this project, you must agree that you have authored 100% of the content, that you have the necessary rights to the content and that the content you contribute may be provided under the project license.

Contributing follows mostly the following steps:

1. For bigger changes, open an [issue](https://github.com/springwolf/springwolf-core/issues) and/or drop by on our discord so that we can coordinate.
2. Fork the repository
   - Create a new branch
3. Apply your changes
   - Add/Adapt tests as necessary
4. Run the tests
   - Run tests locally via `./gradlew test` (includes `unitTest` and `integrationTest` targets, which executes faster)
   - In case there are expected changes to the generated asyncapi artifacts, run `./gradlew test updateAsyncApiJson` to update them using the current (actual) files
5. Run the code formatter
   - We use the palantir code style and disallow wildcard imports
   - Run `./gradlew spotlessApply` to fix most things automatically
6. Commit your changes & push
7. Open a Pull-Request against the springwolf repository
   - Use the provided template to briefly describe why the changes should be included
8. Update the [documentation](https://github.com/springwolf/springwolf.github.io) as necessary
9. Add yourself to the contributors and your company to the users in [README.md](README.md)

