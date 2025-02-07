package Week3;
import java.util.Scanner;

public class KangarooKill {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        double lengthSide;
        double lengthRoad;
        int NumKangaroos;
        int AustralianRoadWidth = 10;
        double RoadkillProb = 1.47;

        System.out.print("Enter side of square in km: ");
        lengthSide = scnr.nextDouble();

        System.out.print("Enter roads length in km: ");
        lengthRoad = scnr.nextDouble();

        System.out.print("Enter number of kangaroos: ");
        NumKangaroos = scnr.nextInt();

        double Density = (lengthSide*lengthRoad)/NumKangaroos;
        double SurfaceArea = AustralianRoadWidth*lengthRoad/1000;
        double NumberOfKills = Density*SurfaceArea*RoadkillProb;
        double NumberOfInjuries = Math.ceil(NumberOfKills%1);

        System.out.println("Expected number of kill is: " + (int)NumberOfKills);
        System.out.print("Expected number of injuries is: " + (int)NumberOfInjuries);

    }
} // end of the kangaroo class
