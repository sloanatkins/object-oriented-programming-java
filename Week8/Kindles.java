package Week8;
import java.util.Scanner;

public class Kindles {
    //instance variable declaration
    private int totalPages;
    private int currentPage;
    //constructor to initialize variables
    public Kindles(int numberOfPages) {
        totalPages = numberOfPages;
        currentPage = 1;
    }

    public String toString() {
        return "Page " + currentPage+" of " + totalPages;
    }
    //turnPage() method
    public void turnPages()
    {
        //if currentPage+1 is greater than numberOfPages,then
        if(currentPage + 1 > totalPages)
        {
            System.out.print("You were on                :  " + toString());
            System.out.println("Turning 1 pages would take you past the last page.");
            currentPage = totalPages;
            System.out.print("You are now on             :  " + toString());

        }
        //otherwise increment currentPage by 1
        else
            currentPage++;

    }
    public void turnPages(int n)
    {
        //if currentPage+n is greater than numberOfPages,then
        if(currentPage + n > totalPages)
        {
            System.out.println("You were on                : " + toString());
            System.out.println("Turning " + n + " pages would take you past the last page.");
            currentPage = totalPages;
            System.out.print("You are now on             : " + toString());

        }
        //otherwise increment currentPage by n
        else
            currentPage = currentPage + n;
    }
}