# Design Patterns

My notes on design patterns and their implementation.


## General Themes

* Separate programs into parts that vary and parts that do not.
    * Generally, a program will need to change it's behavior based on it's input data, while the overarching process remains the same for that class of data.
    * For example, calculating employee wages will vary based on each employee's contract, but the same general process should apply. If the variations among employees can be encapsulated, the core payroll program will not have to change as often.

* Program to an abstraction, not a concrete implementation
    * For parts of a program which interact with a component which varies, it's important to program to an abstraction of that component.
    * Programming to an implementation means relying on details specific to one concrete class. Often a program will work with a certain abstract class of data, among which there will be a number of concrete subclasses. It is impractical to call on different methods for each of the different subclasses. Instead, the program should call on an abstraction of those methods, and allow for each subclass to handle it's own implementation.

* Composition over inheritance
    * Inheritance is a very rigid way of defining classes, which makes it tough to work around when requirements change. On the other hand, defining an objects made up of independent components allows for a much more flexible structure where behaviour can be readily adjusted, even during runtime.


## Patterns

* For a sample implementations, view the respective directory. Code can be run by calling `java <Pattern Name>` from a shell within the `bin` directory.

* Strategy Pattern
    * Definition - The strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.
    * Useful when an algorithm must be performed on varying data types or with varying implementations.

## Sources

* Head First Design Patterns