import java.util.Scanner;

// Abstract Factory Interface =====================================================================

// The abstract factory could be an abstract class or an interface
interface AbstractFactory {
    public ProductA createProductA();
    public ProductB createProductB();
    public ProductC createProductC();
}

// Each product implements an abstract class/interface which clients can interact with
interface ProductA {
    public void printDescriptionA();

    // ... Other methods specific to product A ...
}

interface ProductB {
    public void printDescriptionB();

    // ... Other methods specific to product B ...
}

interface ProductC {
    public void printDescriptionC();

    // ... Other methods specific to product C ...
}


// Concrete Factory Implementations ===============================================================

// Produces products compatible with an OSX environment
class OSXFactory implements AbstractFactory {
    public ProductA createProductA() {
        return new OSXProductA();
    }

    public ProductB createProductB() {
        return new OSXProductB();
    }

    public ProductC createProductC() {
        return new OSXProductC();
    }
}

class OSXProductA implements ProductA {
    public void printDescriptionA() {
        System.out.println("Product A for OSX");
    }
}

class OSXProductB implements ProductB {
    public void printDescriptionB() {
        System.out.println("Product B for OSX");
    }
}

class OSXProductC implements ProductC {
    public void printDescriptionC() {
        System.out.println("Product C for OSX");
    }
}

// Produces products compatible with a Windows environment
class WinFactory implements AbstractFactory {
    public ProductA createProductA() {
        return new WinProductA();
    }

    public ProductB createProductB() {
        return new WinProductB();
    }

    public ProductC createProductC() {
        return new WinProductC();
    }
}

class WinProductA implements ProductA {
    public void printDescriptionA() {
        System.out.println("Product A for Windows");
    }
}

class WinProductB implements ProductB {
    public void printDescriptionB() {
        System.out.println("Product B for Windows");
    }
}

class WinProductC implements ProductC {
    public void printDescriptionC() {
        System.out.println("Product C for Windows");
    }
}


// Using an Abstract Factory ======================================================================

// Using the factory pattern, the client object can function independently of the OS environment
class AbstractFactoryPattern {

    public static void main(String[] args) {
        AbstractFactory factory = getFactory();

        ProductA productA = factory.createProductA();
        ProductB productB = factory.createProductB();
        ProductC productC = factory.createProductC();

        productA.printDescriptionA();
        productB.printDescriptionB();
        productC.printDescriptionC();

        // ... Other method calls on each product ...
    }

    // Determines the user's operating system and creates the appropriate factory
    private static AbstractFactory getFactory() {
        AbstractFactory factory = null;
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("Please enter your operating system (Windows/OSX):");
            String os = in.nextLine();
            if(os.equals("OSX")) {
                factory = new OSXFactory();
            } else if (os.equals("Windows")) {
                factory = new WinFactory();
            } else {
                System.out.println("Invalid input, please enter either Windows or OSX");
            }
        } while(factory == null);

        return factory;
    }
}

// Sample Execution ===============================================================================

/*

$ java AbstractFactoryPattern
Please enter your operating system (Windows/OSX):
OSX
Product A for OSX
Product B for OSX
Product C for OSX

$ java AbstractFactoryPattern
Please enter your operating system (Windows/OSX):
Windows
Product A for Windows
Product B for Windows
Product C for Windows

*/
