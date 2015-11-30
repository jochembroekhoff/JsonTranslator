#JsonTranslator

Whith JsonTranslator you can easily manage the translations for a project which
is using JSON files with translation strings.

## Requirements

* Java 8 or higher
* Write permissions to a folder to use as project folder

## How it works

_Don't have a JAR file? Check out the [Releases page](https://github.com/jochembroekhoff/JsonTranslator/releases), or [build one yourself](#building)._

1. Open a folder, to use as project folder.
2. Go to **Edit** > **Add** > **Translation** and enter information (do this for
   every translation you want).
4. Go to **Edit** > **Add** > **Key** and enter a name (do this for all keys you
   want).
5. Click on the keys at the left side of the window.
6. Edit the strings.
7. **Project** > **Save** project (exports files).

You can give the project a name in **Project** > **Rename**.

## Building

1. Download (and extract) the repo as ZIP or `git clone https://github.com/jochembroekhoff/JsonTranslator.git`.
2. [Install Maven](http://www.mkyong.com/maven/how-to-install-maven-in-windows/) if you haven't yet.
3. Open up a command prompt.
4. Navigate to the folder you've extracted the ZIP or cloned the repo in.
5. Type `mvn install` and hit return, wait a sec.
6. If it's all finished, check the `target` folder, there's a file called `JsonTranslator-(version number).jar`.

You can now use that file and start JsonTranslator.

Or, if you want a pre-built JAR, check out the [Releases page](https://github.com/jochembroekhoff/JsonTranslator/releases).

## License

Licensed under the Zlib license ([`LICENSE.txt`](https://github.com/jochembroekhoff/JsonTranslator/blob/master/LICENSE.txt)).

The zlib license header to use in NetBeans IDE is [`licenseheader.txt`](https://github.com/jochembroekhoff/JsonTranslator/blob/master/licenseheader.txt).