package Sloan_Atkins_CSC120_Project2;

import java.io.Serializable;

class Tree implements Serializable {
    /** variable named species of type TreeSpecies which stores the species of a tree */
    private TreeSpecies species;
    /** variable yearPlanted of type int which holds an integer value representing the year */
    private int yearPlanted;
    /** variable height of type double which holds a double value that stores the height of the tree*/
    private double height;
    /** variable growthRate of type double which holds a double value that stores the growth rate of the tree*/
    private double growthRate;

    /**
     * allows me to create a Tree object with specific characteristics, such as species, planting year, height, and growth rate
     * @param species represents the species of the tree
     * @param yearPlanted represents the year when the tree was planted
     * @param height represents the height of the tree
     * @param growthRate represents the growth rate of the tree
     */
    public Tree(TreeSpecies species, int yearPlanted, double height, double growthRate) {
        this.species = species;
        this.yearPlanted = yearPlanted;
        this.height = height;
        this.growthRate = growthRate;
    }

    /**
     * used to retrieve the species of the tree by providing access to the value of the species variable
     * @return
     */
    public TreeSpecies getSpecies() {
        return species;
    }

    /**
     * used to retrieve the year the tree was planted by providing access to the value of the yearPlanted variable
     * @return
     */
    public int getYearPlanted() {
        return yearPlanted;
    }

    /**
     * used to retrieve the height of the tree by providing access to the value of the height variable
     * @return
     */
    public double getHeight() {
        return height;
    }

    /**
     * serves the purpose of retrieving the growth rate of a tree object
     * @return
     */
    public double getGrowthRate() {
        return growthRate;
    }

    /**
     * responsible for simulating the growth of a tree
     */
    public void grow() {
        height *= (1 + growthRate / 100);
    }
}