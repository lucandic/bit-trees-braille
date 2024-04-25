/* 
 * This is a class for a node that do not store a value, 
 * and can be used for inner nodes in instances of BitTree
 * 
 * @author: Candice Lu
 */

public class BitTreeNode {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

    BitTreeNode left;
    BitTreeNode right;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

    public BitTreeNode(){
      this.left = null;
      this.right = null;
    } // BitTreeNode()
} // class BitTreeNode
