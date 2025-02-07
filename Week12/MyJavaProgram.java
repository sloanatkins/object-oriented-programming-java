package Week12;

public class MyJavaProgram {

    //-------------------------------------------------------------------------------------------------
    public static void main(String[] args) {

        int index;

        for (index = 0; index < args.length; index++) {
            System.out.println(index + " : " + args[index] + " has length " + args[index].length());
        } // end of for loop

        System.out.println("Here is the example of the enhanced for loop: ");

        for(String temporaryData : args) {
            System.out.println(temporaryData);
        }

    }// end of the main
//-------------------------------------------------------------------------------------------------
}
//=================================================================================================
