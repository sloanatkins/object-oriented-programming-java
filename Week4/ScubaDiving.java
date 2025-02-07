package Week4;

import java.util.Scanner;

public class ScubaDiving {

    private static Scanner keyboard = new Scanner(System.in);
    private static final int FEET_PER_ATMOSPHERE = 33;
    private static final double MAX_OXYGEN_PRESSURE = 1.4;
    private static final double CONTINGENCY_OXYGEN_PRESSURE = 1.6;

    public static void main(String[] args) {

        //declare my variables
        int depth;
        int percentOxygen;
        double ambientPressure;
        double partialPressure;
        char oxygenPressureGroup;

        //get the users depth and percent O2 input
        System.out.print("Enter the depth and percentage 02: ");
        depth = keyboard.nextInt();
        percentOxygen = keyboard.nextInt();

        //calculate and print ambient pressure
        ambientPressure = ((double)depth / FEET_PER_ATMOSPHERE + 1);
        System.out.println("Ambient pressure: " + ambientPressure);

        //calculate and print partial pressure
        partialPressure = ((double)percentOxygen/100) * ambientPressure;
        System.out.println("O2 pressure: " + partialPressure);

        //calculate and print oxygen pressure group
        oxygenPressureGroup = ((char)((int)(partialPressure*10) + (int)('A')));
        System.out.println("O2 group: " + oxygenPressureGroup);

        //calculate if the O2 maximal is exceeded and prints
        System.out.print("Exceeds maximal O2 pressure: ");
        if (partialPressure > MAX_OXYGEN_PRESSURE)
            System.out.println("true");
        else
            System.out.println("false");

        //calculate if the O2 contingency is exceeded and prints
        System.out.print("Exceeds contingency O2 pressure: ");
        if (partialPressure > CONTINGENCY_OXYGEN_PRESSURE)
            System.out.println("true");
        else
            System.out.println("false");
        
    }
}
