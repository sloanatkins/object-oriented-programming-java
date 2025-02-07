package Week7;
import java.util.Scanner;

public class iThink {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String line = "";
        String quality = "";

        System.out.println("Please enter sentences, . to end.");
        // Iterate a loop till user enters "."
        while(true) {
            line = scan.nextLine();
            // if line equals . , terminate the loop
            if(line.equals(".")) {
                break;
            }
            // If line starts with "I am", concatenate a substring starting from index 5 to quality with a comma
            if(line.startsWith("I am")) {
                quality += line.substring(5) + ", ";
            }
        }

        // Print result
        System.out.println("The qualities are " + quality);
    }
}


