# Flapi - A fluent API generator for Java
## v0.4 [![Build Status](https://travis-ci.org/UnquietCode/Flapi.png?branch=master)](https://travis-ci.org/UnquietCode/Flapi)

### What is it?
Flapi is a code generation library for creating fluent API's in Java.
Fluent builders allow developers to more easily interact with your code, using a syntax
more akin to natural language.
See [these](http://www.unquietcode.com/blog/2011/programming/using-generics-to-build-fluent-apis-in-java)
[articles](http://martinfowler.com/bliki/FluentInterface.html) for more information.

At the time of writing, the project builds fine in JDK 6 and 7. However, please note that the automated builds are no longer being run against JDK 6.

### [Getting Started](https://github.com/UnquietCode/Flapi/wiki/Getting-Started)
If you are using Maven (or gradle, or Ivy) you can download and install to your local repo, or include the following
repository and dependency in your POM file:
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
  <version>0.4</version>
  <scope>test</scope>
</dependency>
```
In a test, define your `Descriptor` object and output the generated source code. (The
[Pizza Builder](https://github.com/UnquietCode/Flapi/wiki/Pizza-Builder-Example)
example is a simple descriptor you can start with.)

### [Documentation](http://unquietcode.github.io/Flapi)
Please visit the documentation page for a tour of Flapi's features and
how to use them. (generated using the very nice tool [docker](https://github.com/jbt/docker))

### [Examples](https://github.com/UnquietCode/Flapi/wiki/Examples)
Many helpful examples are included on the wiki, corresponding to examples and tests in the src/test directory.

### [Screencast](http://vimeo.com/58855907)
A screencast has been posted to demonstrate some basic usage, and the main purpose of the tool.
(Warning: it's my first attempt at screencasting.)

### [Blog Post](http://www.unquietcode.com/blog/2012/software/introducing-flapi)
The original blog post describing Flapi.

### [User Group](https://groups.google.com/forum/#!forum/flapi-users)
Google Group for asking questions and connecting with other developers using Flapi in their projects.

### What's the project's status?
Version 0.4 has been released, and includes new features and new build tools. The runtime, along with
some generated builders, are currently running in production code.
See the [Release Notes](https://github.com/UnquietCode/Flapi/wiki/Version-0.4) for the full details.

### Problems?
Use the [issue tracker](https://github.com/UnquietCode/Flapi/issues) to report problems encountered or new
feature requests.

### Contributing
Feel free to fork the project and fiddle around! Submit pull requests to improve the code.  
Create issues to help support the project. Ask questions. (Say hello.)

### License
Flapi is provided under the ASL 2.0 license.

## Thanks!

Peace, love, and code.
