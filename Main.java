import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public class Girl {

//--------------------------------------------------------------

        private Girl bestFriend;

        private Boy boyFriend;

//--------------------------------------------------------------

        public Girl(Girl newBestFriend) {



            bestFriend = newBestFriend;

            boyFriend = null;

        }

//--------------------------------------------------------------

        public void addBoyFriend(Boy friend) {


            boyFriend = friend;

        }

//--------------------------------------------------------------

    }

//==============================================================



//==============================================================

    public class Boy {

//--------------------------------------------------------------

        private Girl friend;

//--------------------------------------------------------------

        public Boy(Girl lovesYou) {



            if (lovesYou == null) {

                friend = new Girl(null);

            } else {

                friend = lovesYou;

            }

        }

//--------------------------------------------------------------

    }

//==============================================================



//==============================================================

    public class GirlsAndBoys {

//--------------------------------------------------------------

        public void main(String args) {



            Boy harry,garry;

            Girl sally;



            sally = new Girl(null);

            harry = new Boy(null);

            garry = new Boy(new Girl(sally));

            sally.addBoyFriend(harry);

        }

//--------------------------------------------------------------

    }

//============================================================================================
}