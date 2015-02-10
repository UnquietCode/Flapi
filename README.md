# Flapi - A fluent API generator for Java
## v0.7.3 [![Build Status](https://travis-ci.org/UnquietCode/Flapi.png?branch=master)](https://travis-ci.org/UnquietCode/Flapi) 

[![Tip with Gratipay](https://assets.gratipay.com/gratipay.svg)](https://gratipay.com/UnquietCode) [![Tip with Bitcoin](https://assets.gratipay.com/bitcoin.png)](https://blockchain.info/address/1Ec6mzLpJQvuzXqhxfJz1h9ZwJmoHMW9BX)**Bitcoin**

### What is it?
Flapi is a code generation library for creating fluent API's in Java.
Fluent builders allow developers to more easily interact with your code, using a syntax
more akin to natural language.
See [these](http://www.unquietcode.com/blog/2011/programming/using-generics-to-build-fluent-apis-in-java)
[articles](http://martinfowler.com/bliki/FluentInterface.html) for more information.

##### Flapi turns this:
```java
Descriptor builder = Flapi.builder()
	.setPackage("unquietcode.tools.flapi.examples.email.builder")
	.setStartingMethodName("composeEmail")
	.setDescriptorName("Email")

	.addMethod("subject(String subject)").atMost(1)
	.addMethod("addRecipient(String emailAddress)").atLeast(1)
	.addMethod("sender(String emailAddress)").exactly(1)
	.addMethod("body(String text)").atMost(1)
	.addMethod("send()").last(EmailMessage.class)
.build();
```
##### ...or this:
```java
interface EmailHelper {

	@AtMost(1) void subject(String subject);
	@AtLeast(1) void addRecipient(String emailAddress);
	@Exactly(1) void sender(String emailAddress);
	@Any void addCC(String emailAddress);
	@Any void addBCC(String emailAddress);
	@AtMost(1) void body(String text);
	@Any void addAttachment(File file);
	@Last EmailMessage send();
}

Flapi.create(EmailHelper.class)
	.setPackage("unquietcode.tools.flapi.examples.email.builder")
	.setStartingMethodName("compose")
.build();
```

##### ...into this:
```java
composeEmail()
    .sender("HAL9000@gmail.com")
    .addRecipient("dave@unquietcode.com")
    .subject("Just what do you think you're doing, Dave?")
    .body("I know that you and Frank were planning to disconnect me, " +
          "and I'm afraid that's something I cannot allow to happen...")
.send();
```


### [Getting Started](https://github.com/UnquietCode/Flapi/wiki/Getting-Started)
If you are using Maven (or Gradle, or Ivy) you can download and install to your local repo, or include the following
repository and dependency in your build script:

#### Maven
```xml
<repository>
  <id>uqc</id>
  <name>UnquietCode Repository</name>
  <url>http://www.unquietcode.com/maven/releases</url>
</repository>

...

<dependency>
  <groupId>unquietcode.tools.flapi</groupId>
  <artifactId>flapi</artifactId>
  <version>0.7.3</version>
  <scope>test</scope>
</dependency>
```

#### Gradle
```groovy
repositories {
  maven {
    url 'http://www.unquietcode.com/maven/releases'
  }
}

...

dependencies {
  testCompile 'unquietcode.tools.flapi:flapi:0.7.3'
}
```

In a test define your `Descriptor` object and output the generated source code. (The
[Pizza Builder](https://github.com/UnquietCode/Flapi/wiki/Pizza-Builder-Example)
example is a simple descriptor you can start with.) You can also make use of the
[maven plugin](https://github.com/UnquietCode/Flapi/wiki/Maven-Build-Plugin) to
perform the code generation.

At the time of writing, the project builds fine in JDK 6 and 7. However, please note that
the automated builds are no longer being run against JDK 6.


### Additional Resources

* [Documentation](http://unquietcode.github.io/Flapi)  
Please visit the documentation page for a tour of Flapi's features and
how to use them. (generated using the very nice tool [docker](https://github.com/jbt/docker))

* [Examples](https://github.com/UnquietCode/Flapi/wiki/Examples)  
Many helpful examples are included on the wiki, corresponding to examples and tests in the src/test directory.

* [Blog Post](http://www.unquietcode.com/blog/2012/software/introducing-flapi)  
The original blog post describing Flapi.


### What's the project's status?
Version 0.7 has been released, and includes enhancements for customizing the generated code, plus several
other new features and improvements. See the
[Release Notes](https://github.com/UnquietCode/Flapi/wiki/Version-0.7) for the full details.

### Problems?
Use the [issue tracker](https://github.com/UnquietCode/Flapi/issues) to report problems encountered or new
feature requests.

### Contributing
Feel free to fork the project and fiddle around! Submit pull requests to improve the code.  
Create issues to help support the project. Ask questions. (Say hello.)

[![Tip with Gratipay](https://assets.gratipay.com/gratipay.svg)](https://gratipay.com/UnquietCode) [![Tip with Bitcoin](https://assets.gratipay.com/bitcoin.png)](https://blockchain.info/address/1Ec6mzLpJQvuzXqhxfJz1h9ZwJmoHMW9BX)**Bitcoin**

If you like this software and find it useful, then please consider supporting my efforts through
a donation via [BitCoin](https://gratipay.com/UnquietCode) or [other means](https://gratipay.com/UnquietCode).

Special thanks to [Concurrent, Inc.](http://www.concurrentinc.com) for their feedback and support
as a user of Flapi, which they use in their [Fluid](https://github.com/Cascading/fluid)
library for Cascading.

### License
Flapi is licensed under the Apache Software License (ASL) 2.0

## Thanks!

Peace, love, and code.
