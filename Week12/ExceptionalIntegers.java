package Week12;

import java.util.ArrayList;

public class ExceptionalIntegers {

    public static void main(String[] args) {

        int index;

        ArrayList<Integer> integers = new ArrayList<Integer>();

        for (index = 0; index < args.length; index++) {

            try {
                Integer temporaryVariable = Integer.parseInt(args[index]);
                integers.add(temporaryVariable);
                System.out.println("Converter method says integer OK - " + temporaryVariable);
            }  catch (NumberFormatException e) {
                System.out.println("Catch block says the argument " + '"' + args[index] + '"'+  " is ignored because " + args[index]);
            }
        }

        System.out.println(" ");
        System.out.println("Vector contents are:");

        int count = 0;
        for (Integer temporarydata : integers) {
            System.out.println("Item " + count + " is " + temporarydata);
            count++;
        }

    }
}
