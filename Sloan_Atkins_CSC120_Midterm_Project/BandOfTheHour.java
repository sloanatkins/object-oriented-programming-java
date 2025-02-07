package Sloan_Atkins_CSC120_Midterm_Project;

import java.util.Scanner;

/**
 * Organize where the musicians of the UM Band will stand when they play at away games.
 * @author Sloan Atkins
 */
public class BandOfTheHour {

    /**
     * Max number of rows allowed
     */
    private static final short MAX_ROWS = 10;

    /**
     * Max number of positions allowed
     */
    private static final short MAX_POSITIONS = 8;


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter number of rows: ");

        int numRows = scanner.nextInt();
        while (numRows < 1 || numRows > MAX_ROWS) {
            System.out.print("ERROR: Out of range: ");
            numRows = scanner.nextInt();
        }

        int[] numPositions = new int[numRows];
        for (int i = 0; i < numRows; i++) {
            System.out.printf("Please enter number of positions in row %c: ", 'A' + i);
            numPositions[i] = scanner.nextInt();
            while (numPositions[i] < 1 || numPositions[i] > MAX_POSITIONS) {
                System.out.print("ERROR: Out of range: ");
                numPositions[i] = scanner.nextInt();
            }
        }

        BandOrganizer stadium = new BandOrganizer(numRows, numPositions);

        String userChoice;
        /**
         * Switch loop that prompts the user for a letter and switches the output based on the input
         * Breaks when the input is X
         */
        do {
            System.out.print("\n(A)dd, (R)emove, (P)rint, e(X)it : ");
            userChoice = scanner.next().toLowerCase();

            switch (userChoice) {
                case "p":
                    stadium.printAssignment();
                    break;
                case "a":
                    addMusician(scanner, stadium, numRows);
                    break;
                case "r":
                    removeMusician(scanner, stadium, numRows);
                    break;
                case "x":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("ERROR: Invalid option.");
                    break;
            }
        } while (!userChoice.equals("x"));
    }

    /**
     * Validates the input when adding a musician in addMusician also transfers the information collected from the user
     * to the addMusician method
     * @param scanner allows the method to read input from the user
     * @param stadium allows the method to interact with the BandOrganizer object to add a musician to the stadium
     * @param numRows ensures that the row number entered by the user is within the valid range of rows in the stadium
     */
    private static void addMusician(Scanner scanner, BandOrganizer stadium, int numRows) {
        System.out.print("Please enter row letter (A to " + (char) ('A' + numRows - 1) + "): ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);
        int row = rowLetter - 'A';
        while (row < 0 || row >= numRows) {
            System.out.print("ERROR: Invalid row: ");
            rowLetter = scanner.next().toUpperCase().charAt(0);
            row = rowLetter - 'A';
        }

        System.out.printf("Please enter position number (1 to %d): ", stadium.getNumPositionsInRow(row));
        int position = scanner.nextInt();
        while (position < 1 || position > stadium.getNumPositionsInRow(row)) {
            System.out.print("ERROR: Invalid position: ");
            position = scanner.nextInt();
        }

        System.out.print("Please enter weight (45.0 to 200.0): ");
        double weight = scanner.nextDouble();
        while (weight < 45 || weight > 200) {
            System.out.print("ERROR: Out of range, try again: ");
            weight = scanner.nextDouble();
        }
        stadium.addMusician(row, position, weight);
    }

    /**
     * Validates the input when removing a musician in removeMusician also transfers the information collected from the user
     * to the removeMusician method
     * @param scanner allows the method to read input from the user
     * @param stadium allows the method to interact with the BandOrganizer object to remove a musician to the stadium
     * @param numRows ensures that the row number entered by the user is within the valid range of rows in the stadium
     */
    private static void removeMusician(Scanner scanner, BandOrganizer stadium, int numRows) {
        System.out.print("Please enter row letter (A to " + (char) ('A' + numRows - 1) + "): ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);
        int row = rowLetter - 'A';
        while (row < 0 || row >= numRows) {
            System.out.print("ERROR: Invalid row: ");
            rowLetter = scanner.next().toUpperCase().charAt(0);
            row = rowLetter - 'A';
        }

        System.out.printf("Please enter position number (1 to %d): ", stadium.getNumPositionsInRow(row));
        int position = scanner.nextInt();
        while (position < 1 || position > stadium.getNumPositionsInRow(row)) {
            System.out.print("ERROR: Invalid position: ");
            position = scanner.nextInt();
        }

        stadium.removeMusician(row, position);
    }
}

/**
 Organize where the musicians of the UM Band will stand when they play at away games.
 */
class BandOrganizer {
    /**
     * creates a two-dimensional array named rows of type double.
     */
    private double[][] rows;
    /**
     * maximum weight of a row may not exceed 100kg per position
     */
    private static final short MAX_WEIGHT_PER_POSITION = 100;
    /**
     * maximum weight of a musician
     */
    private static final short MAX_WEIGHT = 200;
    /**
     * minimum weight of a musician
     */
    private static final short MIN_WEIGHT = 25;

    /**
     * Initializes a new stadium object with the specified number of rows and positions per row.
     * @param numRows Integer that holds how many rows the user inputted
     * @param numPositions Integer that holds how many positions in each row the user inputted
     */
    public BandOrganizer(int numRows, int[] numPositions) {
        rows = new double[numRows][];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new double[numPositions[i]];
        }
    }

    /**
     * Prints the current assignment of musicians in the stadium by displaying the weight of musicians
     * assigned to each position in each row, as well as the average weight of all musicians in each
     * column (position) across all rows.
     */
    public void printAssignment() {
        for (int i = 0; i < rows.length; i++) {
            System.out.printf("%c: ", 'A' + i);
            double rowTotalWeight = 0;
            int rowOccupiedPositions = 0;
            for (double weight : rows[i]) {
                System.out.printf("%.1f   ", weight);
                if (weight != 0) {
                    rowTotalWeight += weight;
                    rowOccupiedPositions++;
                }
            }
            double rowAverageWeight = rowTotalWeight / rows[i].length;
            System.out.print("  [");
            double columnTotalWeight = 0;
            int columnOccupiedPositions = 0;
            for (int j = 0; j < rows[i].length; j++) {
                columnTotalWeight += rows[i][j];
                if (rows[i][j] != 0) {
                    columnOccupiedPositions++;
                }
            }

            System.out.printf("%.1f, %.1f]%n", columnTotalWeight, rowAverageWeight);
        }
    }

    /**
     * Adds a musician to a specified position in a given row of the stadium.
     * It performs validation to ensure that the addition adheres to the weight constraints specified.
     * @param row The integer that holds which row the user inputted
     * @param position The integer that holds which position the user inputted
     * @param weight The integer that holds the amount of kilograms the user inputted for the specific musician
     */

    public void addMusician(int row, int position, double weight) {
        if (row < 0 || row >= rows.length || position < 1 || position > rows[row].length) {
            System.out.println("ERROR: Out of range, try again: ");
            return;
        }

        if (rows[row][position - 1] != 0) {
            System.out.println("ERROR: There is already a musician there.");
            return;
        }

        if (weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
            System.out.println("ERROR: Out of range, try again: ");
            return;
        }

        if (totalWeightInRow(row) + weight > MAX_WEIGHT_PER_POSITION * rows[row].length) {
            System.out.println("ERROR: That would exceed the average weight limit.");
            return;
        }

        rows[row][position - 1] = weight;
        System.out.println("****** Musician added.");
    }

    /**
     * Removes a musician from a specified position in a given row of the stadium.
     * It performs validation to ensure that the removal is of a valid musician that was already added.
     * @param row The integer that holds which row the user inputted
     * @param position The integer that holds which position the user inputted
     */
    public void removeMusician(int row, int position) {
        if (row < 0 || row >= rows.length || position < 1 || position > rows[row].length) {
            System.out.println("ERROR: Out of range, try again: ");
            return;
        }

        if (rows[row][position - 1] == 0) {
            System.out.println("ERROR: That position is vacant.");
            return;
        }

        rows[row][position - 1] = 0;
        System.out.println("****** Musician removed.");
    }

    /**
     * Calculates the total weight of all musicians assigned to positions in a specific row of the stadium.
     * @param row The integer that holds which row the user inputted
     */
    private double totalWeightInRow(int row) {
        double totalWeight = 0;
        for (int j = 0; j < rows[row].length; j++) {
            totalWeight += rows[row][j];
        }
        return totalWeight;
    }

    /**
     * Returns the number of positions (seats) in a specific row of the stadium.
     * @param row The integer that holds which row the user inputted
     */
    public int getNumPositionsInRow(int row) {
        return rows[row].length;
    }
}


