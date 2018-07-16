import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

// TODO - add explanatory comments, debug loop in main to generate more than one grade

interface Observable {
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers();
}

interface Observer {
    // Usually the data parameter has the type Object to allow for any kind of data to be passed.
    public void update(Integer data);
}

class ReportCard implements Observable {
    // Observers can be stored in any type of collection. Sets are convenient because observers can be added/removed in constant time
    private Set<Observer> observers;
    private int grade;

    public ReportCard() {
        this.observers = new HashSet<>();
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers() {
        for(Observer o : this.observers) {
            // Data is being "pushed" out to observers
            o.update(this.grade);
        }
    }

    public void generateGrade() {
        Random r = new Random();
        this.grade = r.nextInt(100) + 1;
        notifyObservers();
    }

}

class AverageGradeObserver implements Observer {
    private Observable grades;
    private int numberOfGrades;
    private int gradeSum;

    public AverageGradeObserver(Observable grades) {
        this.grades = grades;
        grades.addObserver(this);
        this.numberOfGrades = 0;
        this.gradeSum = 0;
    }

    public void update(Integer grade) {
        this.numberOfGrades++;
        this.gradeSum += grade;
        display();
    }

    private void display() {
        System.out.println("The average grade is: " + (this.gradeSum / this.numberOfGrades));
    }
}

class CurrentGradeObserver implements Observer {
    private Observable grades;
    private int grade;

    public CurrentGradeObserver(Observable grades) {
        this.grades = grades;
        grades.addObserver(this);
        this.grade = 0;
    }

    public void update(Integer grade) {
        this.grade = grade;
        display();
    }

    private void display() {
        System.out.println("The current grade is: " + this.grade);
    }
}

class ObserverPattern {
    public static void main(String[] args) {
        ReportCard reportCard = new ReportCard();
        Observer averageGrade = new AverageGradeObserver(reportCard);
        Observer currGrade = new CurrentGradeObserver(reportCard);

        Scanner input = new Scanner(System.in);
        do {
            reportCard.generateGrade();
            System.out.println("Press enter to get a new grade, or g to graduate...");
            if(input.hasNext("g")) break;
            input.nextLine();
        } while(true);

        System.out.println("No more grades. You have graduated!");
    }
}