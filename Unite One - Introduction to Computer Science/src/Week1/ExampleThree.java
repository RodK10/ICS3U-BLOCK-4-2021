package Week1;
/**
 * Escape Sequences
 */
public class ExampleThree {
    public static void main(String[] args) {
          //double quotes encapsulates a string literal

          System.out.println("This is \"very\" important!");  //the backslash escapes a character and creates an escape sequence


          //System.out.println("This is \very important!");    // \v is not a valid escape sequence


          System.out.println("This is very \\important!");
          System.out.println("This\nis\nvery\nimportant!");  // \n is a new line and ap exam only tests you on \"" \o \n
    }
    
}
