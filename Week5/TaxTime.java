package Week5;

import jdk.jshell.execution.JdiExecutionControl;
import java.util.Scanner;

public class TaxTime {

    private static Scanner keyboard = new Scanner(System.in);

    private static final int STINKING_RICH = 500000;

    private static final int QUITE_RICH = 200000;

    private static final int MIAMI_POOR = 100000;

    private static final int AVERAGE = 50000;

    private static final int REALISTIC = 20000;

    private static final double HIGH_RATE = 0.25;

    private static final double MEDIUM_RATE = 0.10;

    private static final double LOW_RATE = 0.03;


    public static void main(String[] args) {

        //declaring variables
        double income = 0;
        double deductions = 0;
        int userAmount;

        //getting user input
        System.out.print("Enter next amount: ");
        userAmount = keyboard.nextInt();

        //adding to deductions and the income
        while(userAmount != 0) {
            if (userAmount > 0) {
                income = income + userAmount;
            }
            else {
                deductions = deductions + Math.abs(userAmount);
            }
            System.out.print("Enter next amount: ");
            userAmount = keyboard.nextInt();
        }

        //getting my returned taxable income and tax group
        double taxableIncome = computeTaxableIncome(income,deductions);
        Character taxGroup = computeTaxGroup(taxableIncome);

        //printing everything
        System.out.println("Income = $" + income);
        System.out.println("Deduction = $" + deductions);
        System.out.println("Taxable income = $" + taxableIncome);
        System.out.println("Tax group = " + taxGroup);
        System.out.println("Tax owed = $" + computeTaxesDue(taxGroup,taxableIncome));

    }

    public static double computeTaxableIncome(double income, double deductions) {

        //calculating the taxable income
        if(income >= deductions)
            return income-deductions;
        return 0.0;

    }

    public static Character computeTaxGroup(double taxableIncome) {

        //calculating the tax group based on the taxable income
        if(taxableIncome >= STINKING_RICH) {
            return 'S'; }
        else if(taxableIncome >= QUITE_RICH) {
            return 'Q'; }
        else if(taxableIncome >= MIAMI_POOR) {
            return 'M'; }
        else if(taxableIncome >= AVERAGE) {
            return 'A'; }
        else if(taxableIncome >= REALISTIC) {
            return 'R'; }
        else {
            return 'P'; }

    }

    public static double computeTaxesDue(Character taxGroup,double taxableIncome) {
        //calculating the amount of taxes due using the tax group
        if (taxGroup.equals('S') || taxGroup.equals('Q')) {
            return taxableIncome*HIGH_RATE; }
        else if (taxGroup.equals('M')) {
            return taxableIncome*MEDIUM_RATE; }
        else if (taxGroup.equals('A') || taxGroup.equals('R')) {
            return taxableIncome*LOW_RATE; }
        else if(taxGroup.equals('P')) {
            return 0.0; }
        else {
            return -1; }

    }
}