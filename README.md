# Design Patterns

Notes on design patterns and their implementation.

## Design Principles

* Separate programs into parts that vary and parts that do not.
    * Generally, a program will need to change it's behavior based on it's input data, while the overarching process remains the same for that class of data.
    * For example, calculating employee wages will vary based on each employee's contract, but the same general process should apply. If the variations among employees can be encapsulated, the core payroll program will not have to change as often.

* Program to an abstraction, not a concrete implementation
    * For parts of a program which interact with a component which varies, it's important to program to an abstraction of that component.
    * Programming to an implementation means relying on details specific to one concrete class. Often a program will work with a certain abstract class of data, among which there will be a number of concrete subclasses. It is impractical to call on different methods for each of the different subclasses. Instead, the program should call on an abstraction of those methods, and allow for each subclass to handle it's own implementation.

* Composition over inheritance
    * Inheritance is a very rigid way of defining classes, which makes it tough to work around when requirements change. On the other hand, defining an objects made up of independent components allows for a much more flexible structure where behaviour can be readily adjusted, even during runtime.

* Strive for loosely coupled designs between objects that interact.
    * Loosely coupled means that objects have as little knowledge of each other as possible. When objects do interact, it should be through limited interfaces which do not rely on the concrete details of the objects.
    * Loose coupling allows for objects to change independently of one another, without affecting the rest of the system.

* Classes should be open for extension, but closed for modification (Open-Closed Principle)
    * It is wasteful to alter code which is already written, tested, and known to work. Once a class has been implemented, it should not have to change in response to changing requirements. Instead, a class should be easily extensible, allowing for new code to be built on top of it to meet changing requirements.
    * This principle should only be applied to areas of the application which are likely to change. Knowing what those areas are comes down to experience and having good domain knowledge.
    * Examples
        * The observer pattern allows for a subject object to be extended by its observers without having to change itself.
        * The decorator pattern allows for new functionality to be wrapped around existing objects without modifying the core object.


## Patterns

* For sample implementations, view the respective directory. Code can be run by calling `java <PatternName>` from a shell within the `bin` directory.


### Strategy Pattern

* Useful when an algorithm must be performed on varying data types or with varying implementations.
* Definition - The strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.
    * In Java, each algorithm can be encapsulated by implementing it within its own class. These classes are made interchangeable by implementing a common interface, allowing clients to access any one of them in the same way.


### Observer Pattern

* Useful when the real-time state of a subject object is interesting to an undefined number of observing objects.
* Definition - The observer pattern defines a one-to-many dependency between objects so that when one object changes state, all of its dependents are notified and updated automatically.
    * The one-to-many dependency refers to relationship between a single subject and its many observers.
    * Automatic updating improves efficiency, as each observer would otherwise have to poll the subject constantly.
* Push vs. Pull methods of transmitting data
    * Push method - the subject object will send each observer the exact data it needs.
        * Pros - Observer implementation is simpler and coupling between the observer and subject is minimized.
        * Cons - Complicates subject implementation if there are many types of observers requiring different data sets.
        * Best used when the subject is simple, or there are only few variations on the data required by observers (eg. a thermometer object sharing a single temperature reading).
    * Pull method - the subject merely signals a change has occurred, after which the observer is responsible for pulling data from the subject.
        * Pros - Subject implementation is simpler and many different types of observers can be added easily (i.e. more extensible).
        * Cons - In order to poll the subject, observers need to interact with the concrete implementation of the subject class instead of the generic observable interface. This means the subject and its observers are tightly coupled, meaning that changes to the subject may require changes to the observers.
        * Best used when a subject will have many different types of observers and could be extended in many ways.
* GUI frameworks often make use of this pattern - observers are usually implemented as callback functions (Java wraps lambda functions into observer objects behind the scenes).


### Decorator Pattern

* Definition - The decorator pattern attaches additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.
    * Decorators "attach" to an object through composition. Each decorator maintains a field to the core object it decorates.
    * Decorator classes have the same type as the objects they decorate, either through inheritance or interface implementation. This makes them interchangeable with the original object, and as a result, available for composition to other decorators.
    * Decorators extend functionality by implementing their own logic on top of a call to the core object's methods, or replace the core object's methods entirely.
* The decorator pattern is useful when a system requires many different variations of an object.
* The Java I/O library makes heavy use of decorators to customize input and output stream objects. This makes it possible to easily extend or implement custom streams.
* Drawbacks
    * Using the decorator pattern often results in lots of small classes cluttering up the program. This can make it hard to navigate and understand the system.
    * Decorators mask the concrete type of their core object. This means decoration will break any code relying on the concrete type of an object.
    * Instantiating components with lots of decorators can quickly get out of hand.


## Sources

* Head First Design Patterns