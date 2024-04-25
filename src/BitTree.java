import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;


public class BitTree {
    
    // +--------+------------------------------------------------------
    // | Fields |
    // +--------+

    int size;
    int bitLength;
    BitTreeNode root;
    String cache;

    // +--------------+------------------------------------------------
    // | Constructors |
    // +--------------+

    public BitTree(int n) {
        this.bitLength = n;
        this.root = new BitTreeNode();
        this.size = 0;
        this.cache = null;
    }

    /*
     * follows the path through the tree given by bits (adding nodes as appropriate) 
     * and adds or replaces the value at the end with value. set should throw an 
     * exception if bits is the inappropriate length or contains values other than 0 or 1.
     */
    public void set(String path, String value) throws Exception {
        if (path.length() != this.bitLength) {
            //System.out.println("path length = " + path.length());
            //System.out.println("this.bitLength = " + this.bitLength);
            throw new Exception("Bits are of inappropriate length");
        }
        setHelper(this.root, path, value);
    }

    public BitTreeNode setHelper(BitTreeNode node, String path, String value) throws Exception{

        if (path.equals("")) {
            return new BitTreeLeaf(value);
        }

        int dir = (int)path.charAt(0) - 48;

        if (node == null) {
            node = new BitTreeNode();
        }
        if (dir == 0) {
            //System.out.println(path.substring(1));
            node.left = setHelper(node.left, path.substring(1), value);
        } else if (dir == 1){
            //System.out.println(path.substring(1));
            node.right = setHelper(node.right, path.substring(1), value);
        } else {
            throw new Exception("Contains values other than 0 or 1");
        }
        return node;
    }

    /*
     * follows the path through the tree given by bits, returning the value at the end. 
     * If there is no such path, or if bits is the incorrect length, get should throw an exception.
     */
    public String get(String bits) throws Exception{
        if (bits.length() != this.bitLength) {
            //System.out.println("this.bitLength = " + this.bitLength);
            //System.out.println("path length = " + bits.length());
            throw new Exception("Bits are of inappropriate length");
        }
        BitTreeLeaf leaf = getHelper(root, bits);
        return leaf.value;
    }

    /*
     * Recursively calls itself until it reaches destination
     */
    public BitTreeLeaf getHelper(BitTreeNode node, String bits) throws Exception{
        if (node instanceof BitTreeLeaf) {
            return (BitTreeLeaf) node;
        }

        int dir = (int)bits.charAt(0) - 48;

        if (dir != 0 && dir != 1) {
            //System.out.println((int)("0123".charAt(0)) == 48);
            throw new Exception("Contains values other than 0 or 1");
        } else if (dir == 0){
            return getHelper(node.left, bits.substring(1));
        } else if (dir == 1){
            return getHelper(node.right, bits.substring(1));
        } else {
            throw new Exception("No such path");
        }
    }

    /*
     * prints out the contents of the tree in CSV format. For example, 
     * one row of our braille tree will be “101100,M” 
     */
    public void dump(PrintWriter pen) {
        dumpHelper(pen, this.root, "");
    }

    /*
     * Recursively calls itself until tree is fully traversed
     */
    public void dumpHelper(PrintWriter pen, BitTreeNode node, String bitString) {
        // current node is a BitTreeLeaf, print the value and stop calling
        if (node instanceof BitTreeLeaf) {
            pen.println(bitString + "," + ((BitTreeLeaf)node).value);
            return;
        }
        // current node is null
        if (node == null) {
            return;
        }
        // current node is a BitTreeNode
        dumpHelper(pen, node.left, bitString + "0");
        dumpHelper(pen, node.right, bitString + "1");
    }

    /*
     * reads a series of lines of the form bits, value and stores them in the tree.
     */
    public void load(InputStream source) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(source));
        String line = reader.readLine();
        while (line != null) {
            String[] parts = line.split(",");
            this.set(parts[0], parts[1]);
            line = reader.readLine();
        }
    }
}
