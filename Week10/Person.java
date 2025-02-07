package Week10;

public class Person {

    // Instance variables
    private String name;
    private int age;


    //default constructor
    public Person() {
        name = "";
        age = 0;
    }

    //Constructor
    public Person(String userName, int userAge) {
        name = userName;
        age = userAge;
    }

    @Override
    public String toString() {
        return name + " is " + age + " years old";
    }

    public void incrementAge() {
        age++;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}//end of Person class