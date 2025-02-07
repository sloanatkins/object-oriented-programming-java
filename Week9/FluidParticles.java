package Week9;

import java.util.Scanner;

public class FluidParticles {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Particle tag id? : ");

        int tag = scanner.nextInt();

        Driver particle = new Driver(tag);

        System.out.println(particle);

        while (true) {
            System.out.print("Enter movement: ");
            double dx = scanner.nextDouble();
            double dy = scanner.nextDouble();

            if (dx == 0.0 && dy == 0.0) {
                System.out.println(particle);
                break;
            }

            particle.move(dx, dy);

            System.out.println(particle);
        }

        scanner.close();
    }
}