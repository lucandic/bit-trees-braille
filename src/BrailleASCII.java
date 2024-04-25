import java.io.PrintWriter;

public class BrailleASCII {
    public static void main(String[] args) throws Exception {
        PrintWriter pen = new PrintWriter(System.out, true);
        BrailleASCIITables tables = new BrailleASCIITables();
        String command = args[0];
        String toTranslate = args[1];
        String output = "";
        //tables.brailleToASCII.dump(pen);
        if (command.equals("braille")){
            for (int i = 0; i < toTranslate.length(); i++){
                output += tables.toBraille(toTranslate.charAt(i));
            }
        } else if (command.equals("ascii")) {
            for (int i = 0; i < toTranslate.length(); i+=6){
                //System.out.println("ran: " + i);
                output += tables.toASCII(toTranslate.substring(i, i+6));
                //System.out.println(output);
            }
        } else if (command.equals("unicode")) {
            output = charToUnicode(toTranslate, tables);
        } else {
            System.err.println("Invalid command");
        }
        pen.println(output);
    }

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
    }
}
