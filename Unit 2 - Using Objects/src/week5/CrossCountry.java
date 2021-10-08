package week5;

import java.util.Scanner;

public class CrossCountry {
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);//Creating a new Scanner object class
      processRunner(in);//tells the code that it will take in one runner
      processRunner(in);//tells the code that it will take in another runner
      processRunner(in);//tells the code that it will take the third and final runner
      in.close();//tells the code to close and not take anymore inputs

   }

   private static void processRunner(Scanner in) {
      String firstName, lastName;//Sets these two attributes to a string
      String mileOne, mileTwo, finish;//Sets these three attributes to a string
      String splitTwo, splitThree;//Sets these two attributes to a string

      System.out.print("Please enter your first name: ");//Prints out this message
      firstName = in.nextLine();//This is telling the code that in the next line, it will take a string in called firstName
      System.out.print("Please enter your Last name: ");//Prints out this message
      lastName = in.nextLine();//This is telling the code that in the next line, it will take a string in called lastName
      System.out.print("Please enter mile one: ");//Prints out this message
      mileOne = in.nextLine();//This is telling the code that in the next line, it will take a string in called mileOne
      System.out.print("Please enter mile two: ");//Prints out this message
      mileTwo = in.nextLine();//This is telling the code that in the next line, it will take a string in called mileTwo
      System.out.print("Please enter your finish time: ");//Prints out this message
      finish = in.nextLine();//This is telling the code that in the next line, it will take a string in called finish
      
      
      
      


      /**
       * get the first and last name of the runner get the mileOne, mileTwo and finish
       * times for the runner
       */

      splitTwo = subtractTime(mileTwo, mileOne);//Telling the code that split two is equal to the time of mileTwo - mileOne
      splitThree = subtractTime(finish, mileTwo);//Telling the code that split three is equal to the time of finish - mileTwo

      /**
       * Display a summary for the runner
       */
      System.out.println("Name: " + firstName + " " + lastName);//This sequence of system.outs will print the strings and take in strings such as the running times and name that the user will input

        System.out.println("Mile one: " + mileOne);

        System.out.println("Split two: " + splitTwo);

        System.out.println("Split three: " + splitThree);

        System.out.println("Finish time: " + finish);

   }

   /**
    * 
    * @param endTime
    * @param startTime
    * @return
    */
   private static String subtractTime(String endTime, String startTime) {//A static String class with parameters String end time and start time
      double endTimeInSeconds = convertToSeconds(endTime);//Converting end time in seconds
      double startTimeInSeconds = convertToSeconds(startTime);//Converting start time in seconds

      double diffInSeconds = endTimeInSeconds - startTimeInSeconds;//Setting diff in seconds

      return convertToTime(diffInSeconds);//Convert of time equals the double diff in seconds

   }
   //class.method = static because it belongs to a class
   //object.method = non-static because it belongs to the object
   private static String convertToTime(double timeInSeconds) {//A static string method with parameter double timeInSeconds
      int minutes = (int)(timeInSeconds/60);//Setting int minutes to time in seconds divided by 60 and casting it as an int
      double seconds = (timeInSeconds%60);//Setting double seconds to time in seconds modulus 60


      return String.format("%d:%06.3f" ,minutes, seconds);//Returning this to the code because it is a place holder for integers and doubles

   }

   
   private static double convertToSeconds(String time) {//A static double method with parameter string time
   
    int index = time.indexOf(":");//Setting int index to time.index of

      String front = time.substring(0, index);//setting string front to the first char all the way to the semi column of the time
      String back = time.substring(index + 1);//setting the back from the char right after the semi column

   int frontSeconds = Integer.parseInt(front);//Setting int front seconds to front where we are parsing a string to return as an integer

      frontSeconds *= 60;//We are doing this because we want to turn the seconds into minutes

      Double backSeconds = Double.parseDouble(back);//Now we are parsing the string input of back into a double

      return frontSeconds + backSeconds;//Returning frontSeconds plus backSeconds
   }

}