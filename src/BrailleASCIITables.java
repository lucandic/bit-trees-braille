import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BrailleASCIITables {

    BitTree brailleToASCII;
    BitTree brailleToUnicode;
    BitTree ASCIItoBraille;
    
    public BrailleASCIITables() throws FileNotFoundException, Exception {
        this.brailleToASCII = new BitTree(6);
        this.brailleToASCII.load(new FileInputStream(new File("../lib/BrailleToASCII.txt")));
        this.brailleToUnicode = new BitTree(6);
        this.brailleToUnicode.load(new FileInputStream(new File("../lib/BrailleToUnicode.txt")));
        this.ASCIItoBraille = new BitTree(8);
        this.ASCIItoBraille.load(new FileInputStream(new File("../lib/ASCIIToBraille.txt")));
    }

    /*
     * converts an ASCII character to a string of bits representing 
     * the corresponding braille character
     */
    public String toBraille(char letter) throws Exception {
        String bits = Integer.toBinaryString(Character.valueOf(letter));
        while (bits.length() < 8) {
            bits = "0" + bits;
        }
        //System.out.println("binary string = " + bits);
        return this.ASCIItoBraille.get(bits);
    }

    /*
     * converts a string of bits representing a braille character to the 
     * corresponding ASCII character
     */
    public String toASCII(String bits) throws Exception {
        return this.brailleToASCII.get(bits);
    }

    /*
     * converts a string of bits representing a braille character to the corresponding 
     * Unicode braille character for those bits. You need only support six-bit braille characters.
     */
    public char toUnicode(String bits) throws Exception {
        String unicodeString = this.brailleToUnicode.get(bits);
        System.out.println("Parsed integer = " + Integer.parseInt(unicodeString, 16));
        return Character.toChars(Integer.parseInt(unicodeString))[0];
    }
}
