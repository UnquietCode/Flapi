#Flapi - A fluent API generator for Java
[![Build Status](https://travis-ci.org/UnquietCode/Flapi.png?branch=master)](https://travis-ci.org/UnquietCode/Flapi)

## What is it?
Flapi is a code generation utility for creating fluent API in Java.
Fluent builders allow developers to more easily interact with your code, using a syntax more akin to natural language.
See [these](http://www.unquietcode.com/blog/2011/programming/using-generics-to-build-fluent-apis-in-java)
[articles](http://martinfowler.com/bliki/FluentInterface.html) for more information.

Flapi is now a stable release, with all of the important features implemented. Please help the project
by testing and reporting bugs, either on GitHub or JIRA.

## [Getting Started](https://github.com/UnquietCode/Flapi/wiki/Getting-Started)
You can grab the latest jar from [the repository](https://github.com/UnquietCode/Flapi/tree/master/lib)
directly.

Or, if you are using Maven you can also include the following repository and dependency in your POM file:
```
<repositories>
  <repository>
    <id>uqc</id>
    <name>UnquietCode Repository</name>
    <url>http://www.unquietcode.com/maven/releases</url>
  </repository>
</repositories>

...

<dependency>
  <groupId>unquietcode.tools.flapi</groupId>
  <artifactId>flapi</artifactId>
  <version>0.3</version>
</dependency>
```

## What's New?
Version 0.3 is out and includes bugfixes new features, and some small improvements.

A new example has been added,
[PipedProcessExample](https://github.com/UnquietCode/Flapi/tree/master/src/test/java/unquietcode/tools/flapi/examples/pipes)

See the [Release Notes](https://github.com/UnquietCode/Flapi/wiki/v0_3) for the full details.

## [Screencast](https://vimeo.com/58855907)
A screencast has been posted to demonstrate some basic usage, and the main purpose of the tool.
(Warning: it's my first attempt at screencasting.)

## [Examples](https://github.com/UnquietCode/Flapi/wiki/Examples)
Many helpful examples are included on the wiki, corresponding to examples and tests in the src/test directory.

## [Wiki](https://github.com/UnquietCode/Flapi/wiki)
Some additional information is on the project's wiki.

## [Blog Post](http://www.unquietcode.com/blog/2012/software/introducing-flapi)
The original blog post describing Flapi.

## Problems?
Use the [issue tracker](https://github.com/UnquietCode/Flapi/issues) to report problems encountered or new
feature requests.

## Contributing
Feel free to fork the project and fiddle around! Submit pull requests to improve the code.  
Create issues to help support the project. Ask questions. (Say hello.)

Peace, love, and code.

# Thanks!
