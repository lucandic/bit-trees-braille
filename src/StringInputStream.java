import java.io.InputStream;

/**
 * Treat a string as an InputStream. Why doesn't Java include this
 * already?
 *
 * @author Samuel A. Rebelsky.
 */
public class StringInputStream extends InputStream {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The string we are reading from.
   */
  String str;

  /**
   * The length of that string (so that we don't keep recomputing it.
   */
  int len;

  /**
   * The position in the string.
   */
  int pos;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new input stream for a string.
   */
  public StringInputStream(String str) {
    this.str = str;
    this.len = str.length();
    this.pos = 0;
  } // StringInputStraem

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  @Override
  public int read() {
    if (pos >= len) {
      return -1;
    } else {
      return str.charAt(pos++);
    }
  } // read()

} // class StringInputStream
