# Version 0.8
This version includes several key features, and is also intended to be the last
feature release before 1.0.

### Gradle Plugin
A new build plugin for Gradle is available, extending the build-time support
for code generation offered by the Maven plugin. See the
[wiki page](https://github.com/UnquietCode/Flapi/wiki/Gradle-Build-Plugin)
for more information.

### Block Mixins
Using block mixins, it is possible to 'extend' one or more already existing
blocks, reducing duplication, and covering a common use case. See the
[Block Mixins](http://unquietcode.github.io/Flapi/#block%20mixins) section
in the documentation for usage notes.

### Return of the `Start` wrapper interface.
The `Start<Void>` wrapper interface which was removed previously has been restored.
Every descriptor now includes a simple, untyped interface for public consumption
named `Start`. This interface will extend whatever the internal `Head` interface
demands, creating a more shelf-stable API.

### Features and Improvements

	+ [#25]: new Gradle build plugin
	+ [#44]: support for block mixins
	+ [#216]: support for extended type hierarchies in annotated helpers
	+ [#215]: allow customization of anonymous names via the NameGenerator API
	+ [#214]: support for single wildcard generics in method signatures
	+ [#213]: new `Start` wrapper type, replacing confusing uses of `Start<Void>`

The full list of tasks and issues included in the release is available on the project's
[Issue Tracker](https://github.com/UnquietCode/Flapi/milestones/0.8).

------------------------------------------------

# Version 0.7
The latest version includes some new features for modifying the output
of the generated source code. As well, better type handling is available
for arrays and generics.

### Features and Improvements
	+ [#197]: pluggable class and method name generator
	+ [#14]: support for disabling the printing of timestamps
	+ [#194, #208]: support for generic parameters in annotated helpers
	+ [#94]: support for multi-dimensional array types
	+ [#47]: new `*Factory` interfaces and static factory method
	+ [#33]: update to JDK7, and enable JDK8 builds

### Disabling Timestamps
Timestamps in the generated source can be disabled by calling the new `disableTimestamps()`
method on the fluent builder.

### Pluggable Name Generator
It is now possible to customize the generated names by using different implementations
of the new `NameGenerator` interface. An instance can be provided by calling the new
`useCustomNameGenerator(..)` method on the fluent builder. Several name generators are
provided out of the box:

* `DefaultNameGenerator` - makes no alterations
* `CondensedNameGenerator` - shortens method names
* `TinyNameGenerator` - shortens method and class names
* `HashedNameGenerator` - shortens method and class names using an MD5 hash

### Generics in Annotated Helpers and Return Types
Improving upon the annotations introduced in version 0.6, you can now use generic
parameters and return types in your annotated helper classes. Additionally, all
methods can make use of generic return types.

### Factory Methods
A new `Factory` interface is now created for each descriptor, and contains a
single parameter-less method which can be used to start a new builder. The
`Generator` classes correspondingly have a new method which binds parameters
to a new factory instance and returns it. This functionality is provided as
a shortcut to defining your own factory for downstream consumers.

### BREAKING CHANGE - `@BlockChain` annotation does not have a value
The value for the block chain marker annotation for use in annotated helpers
has been removed through better resolution of generics.

The full list of tasks and issues included in the release is available on the project's
[Issue Tracker](https://github.com/UnquietCode/Flapi/milestones/0.7).

------------------------------------------------

# Version 0.6
Flapi Version 0.6 includes a major update to support annotation based configuration
of descriptors. 

### Annotations
You can now create descriptors from annotated helper classes and interfaces. See the documentation section on [annotations]() for more details.

### Bean Builders
Similar to annotations, you can provide a class with `setXYZ(..)` and `withXYZ(..)` methods, and these will be turned into a simple builder where each method can only be called at most one time, and where a bean will be returned at the end of the chain.

### BREAKING CHANGE - wrapper `Start` interface requires a type parameter
In order to enable proper nested blocks, all `Start` interfaces now require a
type parameter to be provided. In most cases, this value will simply be `Void`.

## Resolved Issues
Issues are now handled through GitHub, and historical issues have been migrated from JIRA.

### Features and Improvements
	+ [FLAPI-188 / #11]: Support for creating descriptors from annotated helper interfaces.
    + [FLAPI-155 / #52]: Support referencing the current block.
	+ [FLAPI-185 / #38]: Add @see to generated documentation pointing to actual Helper methods.
	+ [FLAPI-147 / #36]: Provide Wrapper interface for all blocks.

### Bugs
	+ [FLAPI-165 / #35]: Get build project working in the reactor build.

### Tasks
	+ [FLAPI-190 / #34]: Update docs and wiki with annotations info.
	+ [FLAPI-189 / #17]: Move issues from JIRA to GitHub

The full list of tasks and issues included in the release is available on the project's
[Issue Tracker](https://github.com/UnquietCode/Flapi/milestones/0.6).

------------------------------------------------

# Version 0.5
Flapi Version 0.5 has been released! Here are the highlights.

### Grouped any() Methods
Methods in a descriptor marked as `any()` can now be associated with a group via the `any(int group)` method. This, combined with `atMost(..)`, comprises what is known as [Method Grouping](Method-Grouping-and-Triggering)

### Flapi's builder is now generated on the fly!
The generated source code used by the main descriptor builder is no longer part of the codebase. Instead, it is generated at build time using the maven plugin made available starting with version 0.4. The plugin makes it easier to keep generated code out of your source repositories, and now Flapi itself can reap the benefit. 

### New Flapi User Group on Google
A user group for Flapi has been created on Google Groups. Feel free to ask questions, leave comments, and otherwise interact with other people using the tool. [Check it out!](https://groups.google.com/forum/#!forum/flapi-users)

### No More License
Previous versions of the tool have been released under the ASL 2.0 license. After some serious soul-searching, the license has been removed entirely.

### BREAKING CHANGE - wrapper interface is now 'Start'
Previously, the wrapper interface was called `$`. Flapi 0.5 has removed all uses of the '$' character from the generated classes to address [Flapi-159](https://unquietcode.atlassian.net/browse/FLAPI-159). The wrapper has been changed to the `*Builder.Start` interface generated for each block. While an inconvenience for those making use of the old interface, this flows from the tool's mandate to be compatible with the most number of code editors.

## Resolved Issues

### Improvement
    + [FLAPI-92] - Allows the top level descriptor block to be used recursively.
    + [FLAPI-118] - Support method grouping for any() methods.
    + [FLAPI-156] - Make it possible to declare return types by String instead of by Class, allowing looser coupling.
    + [FLAPI-158] - Remove Flapi builder from source control, use plugin instead.
    + [FLAPI-159] - Remove dollar sign from names, as it has spotty support from tooling.
    + [FLAPI-166] - Detect and reject infinite loops when possible.
    + [FLAPI-171] - Support for grouped atLeast methods.
    + [FLAPI-179] - Support array types throughout.

### Task
    + [FLAPI-121] - Moves example tests into a single test suite.
    + [FLAPI-122] - Removing license from Flapi.
    + [FLAPI-160] - Document wrapper interface in wiki.
    + [FLAPI-161] - Document ExecutionListener interface in wiki.
    + [FLAPI-168] - Document helpers in wiki.
    + [FLAPI-173] - Document method grouping / triggering in wiki.
    + [FLAPI-182] - Create a mailing list / user group for Flapi.

The full list of tasks and issues included in the release is available on the project's
[Issue Tracker](https://github.com/UnquietCode/Flapi/issues?q=milestone%3A0.5+milestone%3A0.5.1).

------------------------------------------------

# Version 0.4
Flapi 0.4 includes several bugfixes, as well as a bunch of new features. This release is larger than the
rest, and has been a long time coming.

### No more *Impl classes!
The biggest change is moving from the generated implementation classes to a single JDK proxy. This means
that only the interfaces need to be generated for your API. A single, shared runtime is provided, and
can even be included with the generated source.

### Triggered Methods
It is now possible to specify that a method should be 'triggered' to appear by another method. Just use
`after(...)` on the method you want to trigger, and tie it via the group number to another method in
the block.

### Generics Support
Where parameters can be specified, generics are now supported. (Sorry, no wildcards or bounded types
just yet.)

### Enum Selectors
A list of enum values can be turned into a single method which fans out to those values. No need to
import the enum at all when using the generated builder!

### $ Wrapper
The top level interface in every block now has an alias, `$`. This class extends from the top level
and is nested inside of it. Classes which need to know this class previously needed to change with
every build. Now the naming can be consistent across generations of your descriptor. (Feature
suggested by Jiri Jetmar. Thanks!)

### Builder Partitioning
The generated classes are now broken up into individual packages for each block.

### Switch From CodeModel to JCodeModel
The CodeModel library has been replaced with a custom fork called
[JCodeModel](https://github.com/UnquietCode/JCodeModel). This version fixes some bugs and adds
support for features required by Flapi's code generation components.

### flapi-runtime
A new runtime jar is available. It is suggested that you include it as a runtime dependency of
your API, however the older style of generating the runtime classes alongside your builder is
still supported.

### Maven Plugin
A maven plugin is available to support building API jars from descriptors on-the-fly at build time.
This greatly simplifies things, allowing you to define a module for your builder API which can
be consumed by your build or shared with others. See the [wiki page](Maven-Build-Plugin) for more
information.

### BREAKING CHANGE - Switched From ObjectWrapper to AtomicReference
Previously, *Helper interfaces made use of a custom `ObjectWrapper` class to provide new instances.
This has been replaced with the JDK `AtomicReference` class. While an annoying change, one of the
goals of this project is to require as few additional classes to function as possible. Removing
this was an easy win, and fortunately the change in your code is slight, typically just the
import statement.

### BREAKING CHANGE - New BlockChainBuilder API
When creating block chains via `addBlockChain()`, the returned `BlockChainBuilder` object's API
has changed. Instead of creating a chain by prepending C to B to A, it is now possible to declare
them as a flat list of A, B, C. This is facilitated by the addition of a `end()` method, which
is required to finish constructing the block chain. Again, change is hard but the project is
still in the 0._x_ development stage, and sometimes these changes are required.

### New Documentation
A brand new documentation page is available at
[http://unquietcode.github.io/Flapi](http://unquietcode.github.io/Flapi). Check it out!


## Resolved Issues

### Bug
    + [FLAPI-28] - Add parsing of type parameters in MethodParser
    + [FLAPI-127] - Builder names are too verbose.

### Feature
    + [FLAPI-126] - Create a wrapper interface to buffer against changes between generations.
    + [FLAPI-128] - Support for triggered methods.
    + [FLAPI-129] - Replace generated implementation classes with a single dynamic proxy.
    + [FLAPI-144] - Maven build plugins
    + [FLAPI-145] - Support typed enum selectors.

### Improvement
    + [FLAPI-22] - Provide trace logging.
    + [FLAPI-23] - builder package partitioning
    + [FLAPI-130] - Switch to custom JCodeModel.
    + [FLAPI-140] - Remove ObjectWrapper in favor of JDK AtomicReference
    + [FLAPI-143] - Introduce a flapi-runtime module for the support classes.
    + [FLAPI-149] - Change BlockChain API

### Task
    + [FLAPI-42] - Enable parameterized types within Flapi methods.
    + [FLAPI-124] - Add java.util to class search path.
    + [FLAPI-139] - Create two-column documentation of Flapi features.

The full list of tasks and issues included in the release is available on the project's
[Issue Tracker](https://github.com/UnquietCode/Flapi/issues?q=milestone%3A0.4).

------------------------------------------------

Flapi 0.3 includes several bugfixes, as well as some new features.

## Javadocs
Documentation is now available using the `withDocumentation`. Because of this, javadocs have been added to the
generated Flapi descriptor classes.

## Deprecation
Methods can now be marked as @Deprecated, which allows an API to be changed more incrementally.
Use the `markAsDeprecated(..)` method on descriptor methods to enable this.

## Method Grouping
Methods which are marked `atMost(..)` can now be grouped together within the same block.
This means that when one goes, so does the other. Use this in place of unnecessary anonymous blocks.
The new documentation methods, for example, make use of this.

## Demos and Examples
A screencast which attempts to explain the basic use of Flapi has been posted [here](http://vimeo.com/58855907/).

As well, a new example has been added, called PipedProcessorExample. This showcases building up computation
instead of an object, by piping processes together. Find it in the
[test directory](https://github.com/UnquietCode/Flapi/tree/master/src/test/java/unquietcode/tools/flapi/examples/pipes).

## Resolved Issues

### Bug
    + [FLAPI-95] - Blocks with no methods should be rejected.
    + [FLAPI-103] - Top level return types not working as expected.
    + [FLAPI-105] - Failure to recognize two classes as different, resulting in compiler error.

### Feature
    + [FLAPI-49] - Add the ability to annotate builder methods as @Deprecated
    + [FLAPI-57] - Allow users to add javadoc comments to interface methods.
    + [FLAPI-102] - Support anonymous blocks.
    + [FLAPI-111] - Support method grouping.

### Improvement
    + [FLAPI-20] - Divide the single 'monolithic' package into subpackages, one per block.
    + [FLAPI-93] - Reject invalid method names in all descriptor methods.
    + [FLAPI-108] - Avoid name collisions in anonymous blocks.
    + [FLAPI-115] - Add Javadocs to Flapi builder methods, now that they are available.

### Task
    + [FLAPI-71] - Reorganize BlockGenerator file
    + [FLAPI-104] - Integrate Travis-CI
    + [FLAPI-106] - Create demo and screen share.
    	+ Watch the video [here](vimeo.com/58855907)
    * [FLAPI-123] - Add PipedProcess example to project.

The full list of tasks and issues included in the release is available on the project's
[Issue Tracker](https://github.com/UnquietCode/Flapi/issues?q=milestone%3A0.3).

------------------------------------------------

# Version 0.2
Flapi 0.2 contains several bugfixes, as well as a complete rearchitecture of the code generation
classes. More javadocs have been added, a few new features, and a few API changes. All of the examples
have been updated. As well, tests have been improved with a suite of in-memory compilation tests, as
well as the inclusion of example code as full-fledged tests.

For clarity, the `once()` method when delaring methods and blocks has been replaced with `exactly(x)`.
`exactly(1)` is equivalent to using `between(1, 1)`.

The `@Generated` annotation is now added to classes when the JDK version is set to >= 6. You can set the target JDK version in Flapi by calling `Flapi.setJDKVersion(...)` and this setting will affect how the classes are generated.

The most important external change is that the 'support' classes and interfaces which comprise the
runtime dependencies of Flapi have been reduced. The required classes are written out with the
generated ones and can be dropped into your application.

A new feature, implicit terminals, allows a block to automatically exit when there are no more methods
left to call. See [this example](House-Builder-Example) for more information.

Key Features:

+ StateClass rearchitecture [FLAPI-83]
+ common interface for implementation classes [FLAPI-67]
+ intermediate values, descriptor value, etc. [FLAPI-50]
+ implicit terminals [FLAPI-37]
+ more javadocs [FLAPI-7]
+ Remove runtime dependencies on the tool. [FLAPI-47] [FLAPI-70]
+ Change 'once' to 'exactly'. [FLAPI-48]
+ [FLAPI-55] - Utilize the @Generated annotation
+ boolean configuration methods no longer require a parameter [FLAPI-59]
+ loaded into maven repo
+ test improvements
	+ [FLAPI-25] - Create a test harness to test known cases of compilation failures.
	+ [FLAPI-56] - Incorporate existing examples into tests.


The full list of tasks and issues included in the release is available on the project's
[Issue Tracker](https://github.com/UnquietCode/Flapi/issues?q=milestone%3A0.2).

------------------------------------------------

# Version 0.1
Flapi is a fluent API generator for Java. By 'fluent' I primarily mean
simplified method chaining, but also the ability to obscure methods which
have been called a certain number of times. This allows a user to see
compile-time errors, or helpful autocomplete suggestions while working
with the API. As well, because the API is generated from a descriptor,
changes are easy to integrate without spending time reworking your
classes.

This version, 0.1, is an initial beta laying the groundwork for using
Flapi. See the [Examples](https://github.com/UnquietCode/Flapi/wiki/Examples)
in the wiki for more information.

The full list of tasks and issues included in the release is available on the project's
[Issue Tracker](https://github.com/UnquietCode/Flapi/issues?q=milestone%3A0.1).
