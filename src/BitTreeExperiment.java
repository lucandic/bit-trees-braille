import java.io.PrintWriter;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/**
 * Some quick experiments with our BitTree class.
 * @author Samuel A. Rebelsky
 */
public class BitTreeExperiment {

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  static PrintWriter pen = new PrintWriter(System.out, true);

  static final String table =
    """
    0000,zero
    0001,one
    0010,two
    0011,three
    0100,four
    1000,eight
    1001,nine
    1100,twelve
    """;;

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Run the experiments.
   */
  public static void main(String[] args) {
    BitTree fourBits = new BitTree(4);

    // Set a few legal values
    pen.println("\nSetting some legal values\n");
    set(fourBits, "0000", "zero");
    set(fourBits, "0010", "two");
    set(fourBits, "1000", "eight");

    // Set a few illegal values
    pen.println("\nSetting some illegal values\n");
    set(fourBits, "0", "zeero");
    set(fourBits, "", "empty");
    set(fourBits, "0103", "three or more");
    set(fourBits, null, "null");

    // Get some legal values
    pen.println("\nGetting some legal values\n");
    get(fourBits, "0000", "zero");
    get(fourBits, "1000", "eight");

    // Get some illegal values
    pen.println("\nGetting some illegal values\n");
    get(fourBits, "0001", "");
    get(fourBits, "0", "");
    get(fourBits, "000x", "");
    get(fourBits, "00001", "");
    get(fourBits, null, "");

    // Change some values
    pen.println("\nChanging some legal values\n");
    set(fourBits, "0000", "0");
    set(fourBits, "0010", "2");

    // Get some changed values
    pen.println("\nGetting those updated legal values\n");
    get(fourBits, "0000", "0");
    get(fourBits, "0010", "2");

    // Import a few more values
    pen.println("\nLoading some values\n");
    load(fourBits, new StringInputStream(table));

    // Get some legal values
    pen.println("\nGetting new values\n");
    get(fourBits, "0000", "zero");
    get(fourBits, "0001", "one");
    get(fourBits, "0010", "two");
    get(fourBits, "0100", "four");
    get(fourBits, "1100", "twelve");

    // Try dumping
    pen.println("\nDumping\n");
    dump(fourBits, table);
  } // main(String[])

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Dump the tree and see if it has the right form.
   */
  static void dump(BitTree bt, String expected) {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintWriter treestream = new PrintWriter(bytes);
    pen.print("dump() ... ");
    pen.flush();
    try {
      bt.dump(new PrintWriter(treestream));
      treestream.close();
      pen.print("dumped ");
      pen.flush();
      // pen.println(bytes.toString());
      String[] dumped = bytes.toString().split("\n");
      String[] original = expected.split("\n");
      Arrays.sort(dumped);
      Arrays.sort(original);
      if (Arrays.equals(dumped, original)) {
        pen.println("OK");
      } else {
        pen.println("FAILED");
        pen.println("  Expected: " + Arrays.toString(original));
        pen.println("  Got:      " + Arrays.toString(dumped));
      } // if/else
    } catch (Exception e) {
      pen.println("FAILED because " + e.toString());
    } // try/catch
  } // dump(BitTree)

  /**
   * Get a value from the tree and print it out.
   */
  static void get(BitTree bt, String bits, String expected) {
    pen.print("get(" + bits + ") = ");
    pen.flush();
    try {
      String result = bt.get(bits);
      pen.print(result);
      pen.flush();
      if (result.equals(expected)) {
        pen.println(" OK");
      } else {
        pen.println(" FAILED");
      }
    } catch (Exception e) {
      pen.println("?? FAILED because " + e.toString());
    } // try/catch
  } // get(BitTree, String)

  /**
   * Load the table.
   */
  static void load(BitTree bt, InputStream source) {
    pen.print("load(...) ... ");
    pen.flush();
    try {
      bt.load(source);
      pen.println("OK");
    } catch (Exception e) {
      pen.println("FAILED because " + e.toString());
    } // try/catch
  } // load(InputStream)

  /**
   * Set a value in the tree. Report success/filure.
   */
  static void set(BitTree bt, String bits, String value) {
    pen.print("set(" + bits + "," + value + ") ... ");
    pen.flush();
    try {
      bt.set(bits, value);
      pen.println("OK");
    } catch (Exception e) {
      pen.println("FAILED b/c " + e.toString());
    } // try/catch
  } // set(BitTree, String, String)
} // class BitTreeExperiment
