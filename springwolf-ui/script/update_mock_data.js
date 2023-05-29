const https = require("https");
const fs = require("fs");

const downloadFile = (url, path) => {
  https.get(url, (res) => {
    const writeStream = fs.createWriteStream(path);

    res.pipe(writeStream);

    writeStream.on("finish", () => {
      writeStream.close();
      console.log("Wrote file to "+path);
    });
  });
}

downloadFile("https://raw.githubusercontent.com/springwolf/springwolf-core/master/springwolf-examples/springwolf-amqp-example/src/test/resources/asyncapi.json", "src/app/shared/mock/mock.springwolf-amqp-example.json")
downloadFile("https://raw.githubusercontent.com/springwolf/springwolf-core/master/springwolf-examples/springwolf-kafka-example/src/test/resources/asyncapi.json", "src/app/shared/mock/mock.springwolf-kafka-example.json")
