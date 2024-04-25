import java.io.PrintWriter;

/* 
 * This is a class that takes one command and one string to be translated, and convert the string into the desired format,
 * as specified by the command
 * 
 * @author: Candice Lu
 */

public class BrailleASCII {
    public static void main(String[] args) throws Exception {
        PrintWriter pen = new PrintWriter(System.out, true);
        BrailleASCIITables tables = new BrailleASCIITables();
        String command = args[0];
        String toTranslate = args[1];
        String output = "";
        // converts ASCII characters to braille
        if (command.equals("braille")){
            for (int i = 0; i < toTranslate.length(); i++){
                output += tables.toBraille(toTranslate.charAt(i));
            }
        // converts braille to ASCII characters
        } else if (command.equals("ascii")) {
            for (int i = 0; i < toTranslate.length(); i+=6){
                output += tables.toASCII(toTranslate.substring(i, i+6));
            }
        // converts ASCII characters to unicode characters
        } else if (command.equals("unicode")) {
            output = charToUnicode(toTranslate, tables);
        } else {
            System.err.println("Invalid command");
            System.exit(1);
        }
        pen.println(output);
    } // main(args)

    /*
     * Converts a string a bits into a string of braille Unicode characters
     */
    public static String charToUnicode(String toTranslate, BrailleASCIITables tables) throws Exception{
        String output = "";
        for (int i = 0; i < toTranslate.length(); i++){
            // to Braille
            String braille = tables.toBraille(toTranslate.charAt(i));
            System.out.println("brailles: " + braille);
            // to unicode character
            char ch = tables.toUnicode(braille);
            System.out.println("output = " + ch);
            output += ch;
        }
        return output;
    } // charToUnicode(toTranslate, tables)
} // class BrailleASCII
