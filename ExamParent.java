//==============================================================

public class ExamParent {

//--------------------------------------------------------------

    protected int numberOfChildren;

    protected String name;

    public static String family;

//--------------------------------------------------------------

    public ExamParent(String name) {



        this.name = name;

        numberOfChildren = 0;

    }

//--------------------------------------------------------------

    public void display() {



        System.out.println(name + numberOfChildren);

    }

//--------------------------------------------------------------

    public String addToName(String suffix) {



        name = name + suffix;



        return(name);

    }

//--------------------------------------------------------------

    public static void renameFamily(String newName) {


        family = newName;

    }

//--------------------------------------------------------------

}

//==============================================================