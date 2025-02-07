package Week2;
import java.util.Scanner;

public class GasLaw {
    //-----------------------------------------------------------------------------
    private static Scanner keyboard = new Scanner(System.in);
//----The gas constant in Joules/mole/K
    private static final double GAS_CONSTANT = 8.3143;
//-----------------------------------------------------------------------------
    public static void main(String[] args) {

//----Variables to hold system values
        double volume,moles,temperature;
        double pressure;


//----What is this all about?
        System.out.print("Enter volume, moles, temperature : ");
        volume = keyboard.nextDouble();
        moles = keyboard.nextDouble();
        temperature = keyboard.nextDouble();

//----Is anything happening here?
        pressure = moles * GAS_CONSTANT * temperature / volume;

//----Why do this?
        System.out.println("Pressure is " + pressure);
    }
//-----------------------------------------------------------------------------
//=============================================================================
}
