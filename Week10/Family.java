package Week10;


public class Family {

    private int numberOfPeople;
    private Person [] family;

    public Family() {
        numberOfPeople = 0;
        family = new Person[10];
    }

    public void display() {
        for (int i = 0; i < numberOfPeople; i++) {
            System.out.println(family[i].toString());
        }
    }

    public boolean addPerson(String name, int age) {
        if (numberOfPeople < 10) {
            family[numberOfPeople] = new Person(name, age);
            numberOfPeople++;
            return true;
        } else {
            return false;
        }
    }

    public void birthday(String name) {
        for (int i = 0; i < numberOfPeople; i++) {
            if (family[i].getName().equals(name)) {
                family[i].incrementAge();
                break;
            }
        }
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public int getTotalAge() {
        int total = 0;
        for (int i = 0; i < numberOfPeople; i++) {
            total += family[i].getAge();
        }
        return total;
    }

}//end of Family class