// Every variation of the algorithm will implement this interface so that
// clients have a consistent interface to the entire family of algorithms
// This is the core of the Strategy pattern
interface GeneralAlgorithm {
    public void runAlgorithm();
}

class AlgorithmOne implements GeneralAlgorithm {
    public void runAlgorithm() {
        // Implementation of a specific algorithm goes here
        System.out.println("Running algorithm 1");
    }
}

class AlgorithmTwo implements GeneralAlgorithm {
    public void runAlgorithm() {
        System.out.println("Running algorithm 2");
    }
}

// This abstract class will be the parent of some data types which will use
// the different algorithm variations. It's not necessary to structure things
// in this way, since any class can instantiate and use AlgorithmOne/Two, but
// doing so will make it easy to apply the algorithm over all valid data types
abstract class AbstractDataType {
    GeneralAlgorithm algorithm;

    // Keep the algorithm field encapsulated
    public void callAlgorithm() {
        algorithm.runAlgorithm();
    }

    // The algorithm field can be changed dynamically, giving classes the
    // ability to vary their behavior as required
    public void setAlgorithm(GeneralAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
}

class DataTypeOne extends AbstractDataType {
    DataTypeOne() {
        this.algorithm = new AlgorithmOne();
    }
}

class DataTypeTwo extends AbstractDataType {
    DataTypeTwo() {
        this.algorithm = new AlgorithmTwo();
    }
}

class StrategyPattern {
    public static void main(String[] args) {
        AbstractDataType one = new DataTypeOne();
        AbstractDataType two = new DataTypeTwo();

        one.callAlgorithm();
        two.callAlgorithm();
        two.setAlgorithm(new AlgorithmOne());
        two.callAlgorithm();

        /* Output:
        Running algorithm 1
        Running algorithm 2
        Running algorithm 1
        */
    }
}
