package Sloan_Atkins_CSC120_Project2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


class Forest implements Serializable {
    /** declares name of type String*/
    private String name;
    /** specifies the data type of the variable trees - it's an ArrayList that contains elements of type Tree*/
    private ArrayList<Tree> trees;

    /**
     * defines a constructor method for the Forest class
     * @param name serves as input to initialize the name attribute of the Forest object being created
     */
    public Forest(String name) {
        this.name = name;
        this.trees = new ArrayList<>();
    }

    /**
     * this method is responsible for retrieving the name of the forest.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * when you call getTrees() on a Forest object, it will return the list of trees in that forest as an ArrayList of Tree objects
     * @return
     */
    public ArrayList<Tree> getTrees() {
        return trees;
    }

    /**
     * it will add the specified Tree object to the list of trees in that forest
     * @param tree represents the tree to be added to the forest
     */
    public void addTree(Tree tree) {

        trees.add(tree);
    }

    /**
     * it will cut down the tree at the specified index in the list of trees in that forest
     * @param index represents the index of the tree to be cut down in the list of trees
     */
    public void cutTree(int index) {
        if (index < 0 || index >= trees.size()) {
            throw new IndexOutOfBoundsException("Tree index out of bounds");
        }
        trees.remove(index);
    }

    /**
     * executes the code inside the method to simulate the growth of trees in the forest
     */
    public void growForest() {
        for (Tree tree : trees) {
            tree.grow();
        }
    }

    /**
     * cuts down trees in the forest based on the specified height threshold
     * @param heightThreshold represents the max height that a new tree can be
     */
    public void reapForest(double heightThreshold) {
        List<Integer> indexesToRemove = new ArrayList<>(); // Store indexes of trees to be removed
        List<Object[]> replacements = new ArrayList<>();

        // Find trees above the height threshold and mark their indexes
        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            if (tree.getHeight() > heightThreshold) {
                System.out.printf("Reaping the tall tree  %-6s %5d %8.2f'%6.1f%%%n", tree.getSpecies(), tree.getYearPlanted(), tree.getHeight(), tree.getGrowthRate());
                indexesToRemove.add(i);

                // Replace with new randomly generated tree
                Random random = new Random();
                TreeSpecies species = TreeSpecies.values()[random.nextInt(TreeSpecies.values().length)];
                int yearPlanted = 2015 + random.nextInt(10); // Random year between 2015 and 2024
                double height = 10 + random.nextDouble() * 10; // Random height between 10 and 20 feet
                double growthRate = 10 + random.nextDouble() * 10; // Random growth rate between 10% and 20%
                Tree newTree = new Tree(species, yearPlanted, height, growthRate);
                Object[] replacement = new Object[]{i, newTree}; // Store the index and corresponding new tree
                replacements.add(replacement);
                System.out.printf("Replaced with new tree %-6s %5d %8.2f'%6.1f%%%n", newTree.getSpecies(), newTree.getYearPlanted(), newTree.getHeight(), newTree.getGrowthRate());
            }
        }

        // Remove trees from the forest
        for (int i = indexesToRemove.size() - 1; i >= 0; i--) {
            trees.remove((int) indexesToRemove.get(i));
        }

        // Add new trees back to the forest at original indexes
        for (Object[] replacement : replacements) {
            int index = (int) replacement[0];
            Tree newTree = (Tree) replacement[1];
            trees.add(index, newTree);
        }
    }

    /**
     * responsible for calculating the average height of all the trees in the forest
     * @return
     */
    public double calculateAverageHeight() {
        double totalHeight = 0;
        int treeCount = trees.size(); // Get the number of trees
        if (treeCount > 0) {
            // Calculate total height
            for (Tree tree : trees) {
                totalHeight += tree.getHeight();
            }
            // Calculate and return the average height
            return totalHeight / treeCount;
        } else {
            // If there are no trees, return 0
            return 0;
        }
    }

    /**
     * responsible for printing out the details of all the trees in the forest
     */
    public void printForest() {
        System.out.println("\nForest name: " + name);
        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            System.out.printf("%5d %-6s %5d %8.2f' %5.1f%%%n",
                    i, tree.getSpecies(), tree.getYearPlanted(), tree.getHeight(), tree.getGrowthRate());
        }
        System.out.printf("There are %d trees, with an average height of %.2f%n", trees.size(), calculateAverageHeight());
    }

    /**
     * responsible for saving the forest data to a file
     */
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name + ".forest"))) {
            oos.writeObject(this);
            System.out.println("Forest saved to file: " + name + ".forest");
        } catch (IOException e) {
            System.err.println("Error saving forest to file: " + e.getMessage());
        }
    }

}