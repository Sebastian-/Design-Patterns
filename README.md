# Design Patterns

Notes on design patterns and their implementation.

## Design Principles

* Separate programs into parts that vary and parts that do not.
    * Generally, a program will need to change it's behavior based on it's input data, while the overarching process remains the same for that class of data.
    * For example, calculating employee wages will vary based on each employee's contract, but the same general process should apply. If the variations among employees can be encapsulated, the core payroll program will not have to change as often.

* Program to an abstraction, not a concrete implementation
    * For parts of a program which interact with a component which varies, it's important to program to a stable abstraction of that component.
    * Programming to an implementation means relying on details specific to one concrete class. Often a program will work with a certain abstract class of data, among which there will be a number of concrete subclasses. It is impractical to call on different methods for each of the different subclasses. Instead, the program should call on an abstraction of those methods, and allow for each subclass to handle it's own implementation.

* Composition over inheritance
    * Inheritance is a very rigid way of defining classes, which makes it tough to work around when requirements change. On the other hand, defining an objects made up of independent components allows for a much more flexible structure where behaviour can be readily adjusted, even during runtime.

* Strive for loosely coupled designs between objects that interact.
    * Loosely coupled means that objects have as little knowledge of each other as possible. When objects do interact, it should be through limited interfaces which do not rely on the concrete details of the objects.
    * Loose coupling allows for objects to change independently of one another, without affecting the rest of the system.

* Classes should be open for extension, but closed for modification (Open-Closed Principle)
    * It is wasteful to alter code which is already written, tested, and known to work. Once a class has been implemented, it should not have to change in response to changing requirements. Instead, a class should be easily extensible, allowing for new code to be built on top of it to meet changing requirements.
    * This principle can make programs unnecessarily complicated, and should only be applied to areas which are likely to see change. Knowing what those areas are comes down to experience and having good domain knowledge.
    * Examples
        * The observer pattern allows for a subject object to be extended by its observers without having to change itself.
        * The decorator pattern allows for new functionality to be wrapped around existing objects without modifying the core object.

* Dependency Inversion Principle
    * Depend upon abstractions. Do not depend upon concrete classes.
    * Similar to "Program to an abstraction, not a concrete implementation" but focuses more on the structure of a program rather than its implementation.
    * High-level modules should not depend on low-level modules, both should depend on abstractions
        * A high-level module is one which makes use of other modules to define its behavior. Instead of using the lower-level modules directly, both should conform to a common interface. In this way, the implementation of both can change without breaking the interaction bewteen them.
    * Abstractions should not depend on details, details should depend on abstractions
        * Said another way, abstractions should not change as details about the underlying objects change. Instead, changing details should stay within the bounds of their object's abstraction.


## Patterns

For sample implementations, view the respective directory. Code can be run by calling `java <PatternName>` from a shell within the `bin` directory.


### Strategy Pattern

* Definition - The strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.
    * In Java, each algorithm can be encapsulated by implementing it within its own class. These classes are made interchangeable by implementing a common interface, allowing clients to access any one of them in the same way.
* Useful when an algorithm must be performed on varying data types or with varying implementations.


### Observer Pattern

* Definition - The observer pattern defines a one-to-many dependency between objects so that when one object changes state, all of its dependents are notified and updated automatically.
    * The one-to-many dependency refers to relationship between a single subject and its many observers.
    * Automatic updating improves efficiency, as each observer would otherwise have to poll the subject constantly.
* Useful when the changing state of a subject object is interesting to an undefined number of observing objects.
* Push vs. Pull methods of transmitting data
    * Push method - the subject object will send each observer the exact data it needs.
        * Pros - Observer implementation is simpler and coupling between the observer and subject is minimized.
        * Cons - Complicates subject implementation if there are many types of observers requiring different data sets.
        * Best used when the subject is simple, or there are only few variations on the data required by observers (eg. a thermometer object sharing a single temperature reading).
    * Pull method - the subject merely signals a change has occurred, after which the observer is responsible for pulling data from the subject.
        * Pros - Subject implementation is simpler and many different types of observers can be added easily (i.e. more extensible).
        * Cons - In order to poll the subject, observers need to interact with the concrete implementation of the subject class instead of the generic observable interface. This means the subject and its observers are tightly coupled, meaning that changes to the subject may require changes to the observers.
        * Best used when a subject will have many different types of observers and needs to be extended in many ways.
* GUI frameworks often make use of this pattern - observers are usually implemented as callback functions (Java wraps lambda functions into observer objects behind the scenes).


### Decorator Pattern

* Definition - The decorator pattern attaches additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.
    * Decorators "attach" to an object through composition. Each decorator maintains a referrence to the core object it decorates.
    * Decorator classes have the same type as the objects they decorate, either through inheritance or interface implementation. This makes them interchangeable with the original object, and as a result, available for composition with other decorators.
    * Decorators extend functionality by implementing their own logic on top of a call to the core object's methods, or replace the core object's methods entirely.
* The decorator pattern is useful when a system requires many different variations of an object.
* The Java I/O library makes heavy use of decorators to customize input and output stream objects. This makes it possible to easily extend or implement custom streams.
* Drawbacks
    * Using the decorator pattern often results in lots of small classes cluttering up the program. This can make it hard to navigate and understand the system.
    * Decorators mask the concrete type of their core object. This means decoration will break any code relying on the concrete type of an object.
    * Instantiating components with lots of decorators can quickly get out of hand.


### Factory Pattern

* There are two common variations on the factory pattern: the factory method pattern, and the abstract factory pattern. Both are meant to encapsulate object creation, but go about it in different ways.
* Factory Method Pattern
    * Definition - The factory method pattern defines an interface for creating an object, but lets subclasses decide which class to instantiate. This allows a class to defer instantiation to subclasses.
        * The "interface for creating an object" includes the factory method meant for instantiating objects, as well as the interface the instantiated objects must adhere to.
        * By using this pattern, a class can define functionality which makes use of the objects a factory method will produce, without being tied to a single, concrete implementation of those objects.
        * Subclasses will implement the factory method to instantiate relevant objects for themselves.
* Abstract Factory Pattern
    * Definition - Provides an interface for creating families of related or dependent objects without specifying their concrete classes.
        * One interface defines the factory object itself, with each method responsible for instantiating an object of a family/category.
        * Each family/category of objects must also implement a common interface. This ensures that a client of the factory will be decoupled from the underlying concrete objects.
    * Essentially an extended version of the factory method pattern. It ties together the creation of related objects through the use of multiple factory methods in a single object.
    * Abstract factory objects are used by clients to instantiate the objects they require. This differs from the factory method pattern, where subclasses implement and use the factory method directly.


### Singleton Pattern

* Definition - The singleton pattern ensures a class has only one instance, and provides a global point of access to it.
* This pattern is useful in providing a single point of control for a resource. It can be used to contain some global state, or act as a controller for some other subsystem (eg. a pool of threads or network connections).
* Implementation details
    * The class' constructor is private. This ensures that the class cannot be instantiated from the outside.
    * The class contains a private field of its own type to which it assigns a single instance of itself.
    * Access to the singleton is managed by a globally available method (in Java this is a static method) which instantiates the object if it hasn't already been created.
    * Potential Problems - see sample implementation for more details
        * When instantiating the singleton through its access method, some scenarios involving multiple threads can lead to creating more than one singleton object.
            * If performance isn't critical (i.e. the singleton is not accessed often), mark the method used to access the singleton as `synchronized`. The synchronized keyword ensures that only a single thread can access a method at one time. This can decrease performance by a factor of 100 in some cases.
            * If the singleton object is always used and is not expensive to create, simply instantiate it from the very beginning.
            * Double-checked locking is a middle-ground between the previous two solutions. It allows for lazy instantiation of the object, but does incur some overhead by synchronizing the instantiation of the object on the first time through. The features required for this strategy are not available in Java 1.4 or earlier.
            * The preferred thread-safe solution is to use a strategy called [Initialization on Demand Holder Idiom](https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom). This strategy can fail if the instantiation of the singleton is not guaranteed to succeed.
        * In very early versions of Java (< 1.2) singletons would be garbage collected unless an outside referrence to them was maintained.

## Sources

* Head First Design Patterns
