package Week7;

public class Student {

    private String studentName;

    private double gpa;

    public Student (){
        studentName = "No name yet...";
        gpa = 4.0;

    }//end of default constructor

    public Student (String theName, double theGPA){
        studentName = theName;
        gpa = theGPA;
    }

    public String getStudentName (){
        return studentName;
    }

    public void setStudentName (String theName){

    }

    public Student(String studentName) {
        this.studentName = studentName;
    }

    public Student(double gpa) {
        this.gpa = gpa;
    }
}


