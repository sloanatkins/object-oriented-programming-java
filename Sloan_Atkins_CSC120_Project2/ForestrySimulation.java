package Sloan_Atkins_CSC120_Project2;
import java.io.*;
import java.util.*;

/**
 * Creates a simple simulation of the growth and pruning of forests
 * @author Sloan Atkins
 */

public class ForestrySimulation {
    private static Scanner scanner = new Scanner(System.in);
    /**declares a private static variable named forests of type `ArrayList*/
    private static ArrayList<Forest> forests = new ArrayList<>();
    /** declares currentForestIndex and initializes it to 0*/
    private static int currentForestIndex = 0;
    /** minimum height of a planted tree*/
    private static final short MIN_HEIGHT = 10;
    /** maximum height of a planted tree*/
    private static final short MAX_HEIGHT = 20;
    /** minimum growth factor of a planted tree*/
    private static final short MIN_GROWTH = 10;
    /** maximum growth factor of a planted tree*/
    private static final short MAX_GROWTH = 20;
    /** minimum years of a planted tree */
    private static final short MIN_YEAR = 2000;
    /** maximum years of a planted tree */
    private static final short MAX_YEAR = 2000;
    /** division to turn  */
    private static final double DIVISION = 10.0;

    /**
     * @param args is an array of strings that contains any command-line arguments passed to the program when it is executed
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No forest names provided. Exiting...");
            return;
        }

        boolean initialized = false; // Flag to track if initialization is done
        for (String forestName : args) {
            Forest forest = loadForestFromCSV(forestName + ".csv");

            while(forest == null) {
                System.out.println("Error opening/reading " + forestName + ".csv");
                break;
            }

            if (forest != null) {
                forests.add(forest);
                if (!initialized) {
                    System.out.println("Welcome to the Forestry Simulation");
                    System.out.println("----------------------------------");
                    System.out.println("Initializing from " + forests.get(0).getName());
                    initialized = true;
                }
            }
        }

        if (!initialized) {
            System.out.println("No valid forest files found. Exiting...");
            return;
        }


        while (true) {
            System.out.print("\n(P)rint, (A)dd, (C)ut, (G)row, (R)eap, (S)ave, (L)oad, (N)ext, e(X)it : ");
            String input = scanner.nextLine().trim().toLowerCase();
            /**
             * Switch loop processes user input to determine which action to take in the forestry simulation program.
             */
            switch (input) {
                case "p":
                    printCurrentForest();
                    break;
                case "a":
                    addTree();
                    break;
                case "c":
                    cutTree();
                    break;
                case "g":
                    growForest();
                    break;
                case "r":
                    reapForest();
                    break;
                case "s":
                    saveCurrentForest();
                    break;
                case "l":
                    loadForest();
                    break;
                case "n":
                    moveNextForest();
                    break;
                case "x":
                    System.out.println("Exiting the Forestry Simulation");
                    return;
                default:
                    System.out.println("Invalid menu option, try again");
            }
        }
    }

    /**
     * Prints the details of the current forest to the console
     */
    private static void printCurrentForest() {
        Forest currentForest = forests.get(currentForestIndex);
        currentForest.printForest();
    }

    /**
     * Randomly generates a new tree to the current forest in the forestry simulation program using
     * random values for the tree's species, year planted, height, and growth rate
     */
    private static void addTree() {
        Random random = new Random();
        TreeSpecies species = TreeSpecies.values()[random.nextInt(TreeSpecies.values().length)];
        int yearPlanted = random.nextInt(MAX_YEAR) + MIN_YEAR; // Random year between 2000 and 2024
        double height = MIN_HEIGHT + random.nextDouble() * (MAX_HEIGHT - MIN_HEIGHT);
        height = Math.round(height * DIVISION) / DIVISION;// Random height between 10 and 20 feet
        double growthRate = MIN_GROWTH + random.nextDouble() * (MAX_GROWTH - MIN_GROWTH);
        growthRate = Math.round(growthRate * DIVISION) / DIVISION; // Random growth rate between 0 and 30%

        Forest currentForest = forests.get(currentForestIndex);
        currentForest.addTree(new Tree(species, yearPlanted, height, growthRate));
    }

    /**
     * Cuts a current tree from the forest while randomly generates a new tree to the current forest in the forestry
     * simulation program using random values for the tree's species, year planted, height, and growth rate
     */
    private static void cutTree() {
        while (true) {
            System.out.print("Enter tree number to cut down: ");
            String input = scanner.nextLine().trim();
            try {
                int index = Integer.parseInt(input);
                Forest currentForest = forests.get(currentForestIndex);
                List<Tree> trees = currentForest.getTrees();
                if (index >= 0 && index < trees.size()) {
                    currentForest.cutTree(index);
                    break; // Exit the loop if input is valid
                } else {
                    System.out.println("Tree number " + index + " does not exist");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("That is not an integer");
            }
        }
    }

    /**
     * simulates the growth of all trees in the current forest by retrieves the current forest from the forests list
     * using the currentForestIndex variable, iterates through each tree in the forest, and then imulates the growth
     * of the tree by increasing its height based on its growth rate
     */
    private static void growForest() {
        Forest currentForest = forests.get(currentForestIndex);
        currentForest.growForest();
    }

    /**
     * simulates reaping trees in the current forest based on the height inputted by the user by iterates through each
     * tree in the forest and for each tree whose height exceeds the provided threshold, it displays information about
     * the tree being reaped, removes it from the forest, and replaces it with a new randomly generated tree of
     * appropriate species, year planted, height, and growth rate
     */
    public static void reapForest() {
        while (true) {
            System.out.print("Height to reap from: ");
            String input = scanner.nextLine().trim();
            try {
                double heightThreshold = Double.parseDouble(input);
                // If the input is a valid double, proceed with reaping forest
                Forest currentForest = forests.get(currentForestIndex);
                currentForest.reapForest(heightThreshold);
                break; // Exit the loop if input is valid
            } catch (NumberFormatException e) {
                System.out.println("That is not an integer");
            }
        }
    }

    /**
     * retrieves the current forest from the forests list using the currentForestIndex variable saveToFile()
     * method of the Forest class writes the forest object to a file in binary format using serialization
     */
    private static void saveCurrentForest() {
        Forest currentForest = forests.get(currentForestIndex);
        currentForest.saveToFile();
    }

    /**
     * asks for the forest name to be loaded and appends the .forest extension to the provided forest name to form the filename.
     * If the forest is successfully loaded, it replaces the current forest in the forests list at the currentForestIndex
     * with the loaded forest.
     */
    private static void loadForest() {
        System.out.print("\nEnter forest name to load: ");
        String forestName = scanner.nextLine().trim();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(forestName + ".forest"))) {
            Forest forest = (Forest) ois.readObject();
            forests.set(currentForestIndex, forest);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error opening/reading " + forestName + ".forest");
            System.out.println("Old forest retained");
        }
    }

    /**
     * takes a parameter fileName, which represents the name of the CSV file containing the forest data. It opens and read
     * the specified CSV file using a BufferedReader. It creates a new Tree object with the extracted data and adds it
     * to the Forest object. It returns the populated Forest object.
     * @param fileName a string that represents the name of the CSV file from which the forest data will be loaded.
     * @return
     */
    private static Forest loadForestFromCSV(String fileName) {
        File file = new File(fileName);
        if (!file.exists() || !file.canRead()) {
            return null; // Skip if file doesn't exist or cannot be read
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            Forest forest = new Forest(fileName.substring(0, fileName.lastIndexOf('.')));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                TreeSpecies species = TreeSpecies.valueOf(data[0].toUpperCase());
                int yearPlanted = Integer.parseInt(data[1]);
                double height = Double.parseDouble(data[2]);
                double growthRate = Double.parseDouble(data[3]);
                forest.addTree(new Tree(species, yearPlanted, height, growthRate));
            }
            return forest;
        } catch (IOException e) {
            // Skip printing the error message for file loading issues
            return null;
        } catch (IllegalArgumentException e) {
            // Skip printing the error message for invalid data in the CSV file
            return null;
        }
    }

    /**
     * moves to the next forest in the list of forests and initializes the program to work with the new forest
     */
    private static void moveNextForest() {

        currentForestIndex = (currentForestIndex + 1) % forests.size();

        // Check if the next forest file exists
        if (currentForestIndex >= forests.size()) {
            System.out.println("No more forests available. Exiting...");
            System.exit(0);
        }

        Forest nextForest = forests.get(currentForestIndex);

        System.out.println("Moving to the next forest: " + nextForest.getName());
        System.out.println("Initializing from " + nextForest.getName());

        String csvFileName = nextForest.getName() + ".csv";
        java.io.File csvFile = new java.io.File(csvFileName);

        if (!csvFile.exists() || !csvFile.canRead()) {
            System.out.println("Error opening/reading " + csvFileName);
            moveNextForest(); // Move to the next forest if the current one is invalid
            return;
        }

    }
}