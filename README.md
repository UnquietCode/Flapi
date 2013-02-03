#Flapi - A fluent API generator for Java
[![Build Status](https://travis-ci.org/UnquietCode/Flapi.png?branch=master)](https://travis-ci.org/UnquietCode/Flapi)

## What is it?
Flapi is a code generation utility for creating fluent API in Java.
Fluent builders allow developers to more easily interact with your code, using a syntax more akin to natural language. See [these](http://www.unquietcode.com/blog/2011/programming/using-generics-to-build-fluent-apis-in-java) [articles](http://martinfowler.com/bliki/FluentInterface.html) for more information.

Flapi is now a stable release, with all of the important features implemented. Please help the project by testing and reporting bugs, either on GitHub or JIRA.

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
  <version>0.2</version>
</dependency>
```

## What's New?
Version 0.2 is out and includes bugfixes, a rearchitecture of the underlying code generation logic, and a few
changes to the API. New features include multiple return values and implicit terminals. See the
[Release Notes](https://github.com/UnquietCode/Flapi/wiki/v0_2) for the full details. The [House Builder example](https://github.com/UnquietCode/Flapi/wiki/House-Builder-Example) 
demonstrates both implicit terminals and returning multiple values.

As well, the 'support' classes are now written out alongside the descriptors, which means that Flapi is no longer required as a runtime dependency when using your generated builders. (yay!)

## [Examples](https://github.com/UnquietCode/Flapi/wiki/Examples)
Many helpful examples are included on the wiki, corresponding to examples and tests in the src/test directory.

## [Blog Post](http://www.unquietcode.com/blog/2012/software/introducing-flapi)
The original blog post describing Flapi.

## Problems?
Use the [issue tracker](https://github.com/UnquietCode/Flapi/issues) to report problems encountered or new
feature requests.

## Contributing
Feel free to fork the project and fiddle around! Submit pull requests to improve the code. Create issues to help support the project.

# Thanks!
