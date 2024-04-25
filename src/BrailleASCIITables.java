import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/* 
 * This is a class that stores BitTrees that can be used to convert ASCII, unicode, and braille characters
 * 
 * @author: Candice Lu
 */

public class BrailleASCIITables {

    // +--------+------------------------------------------------------
    // | Fields |
    // +--------+

    // BitTree that converts braille to ASCII character
    BitTree brailleToASCII;
    // BitTree that converts braille to Unicode string
    BitTree brailleToUnicode;
    // BitTree that converts Unicode string to Braille string
    BitTree ASCIItoBraille;
    
    // +--------------+------------------------------------------------
    // | Constructors |
    // +--------------+  

    public BrailleASCIITables() throws FileNotFoundException, Exception {
        this.brailleToASCII = new BitTree(6);
        this.brailleToASCII.load(new FileInputStream(new File("../lib/BrailleToASCII.txt")));
        this.brailleToUnicode = new BitTree(6);
        this.brailleToUnicode.load(new FileInputStream(new File("../lib/BrailleToUnicode.txt")));
        this.ASCIItoBraille = new BitTree(8);
        this.ASCIItoBraille.load(new FileInputStream(new File("../lib/ASCIIToBraille.txt")));
    } // BrailleASCIITables()

    // +---------+------------------------------------------------------
    // | Methods |
    // +---------+

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
    } // toBraille(letter)

    /*
     * converts a string of bits representing a braille character to the 
     * corresponding ASCII character
     */
    public String toASCII(String bits) throws Exception {
        return this.brailleToASCII.get(bits);
    } // toASCII(bits)

    /*
     * converts a string of bits representing a braille character to the corresponding 
     * Unicode braille character for those bits. You need only support six-bit braille characters.
     */
    public char toUnicode(String bits) throws Exception {
        String unicodeString = this.brailleToUnicode.get(bits);
        return Character.toChars(Integer.parseInt(unicodeString, 16))[0];
    } // toUnicode(bits)
} // class BrailleASCIITables
