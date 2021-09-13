package Week2;
/**
 * Type Casting
 */

public class ExampleEight {
    public static void main(String[] args) {
        final int NUM_MARKS = 5;

        int markOne = 78, markTwo= 87, markThree = 64, markFour = 95, markFive = 84;

        double average = (double)(markOne + markTwo + markThree + markFour + markFive) /NUM_MARKS;


        System.out.println("The average is: "+ average);

        double y = 3.7;
        int x = (int)y;

        double z = (double)7;
        //int --> double //widening conversion
        //double --> int //narrowing conversion 

        //boolean x = (int)true;


        
    }
    
}
