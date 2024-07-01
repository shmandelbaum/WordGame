// ListNode.java
/**
 * Represents a node in a linked list of words.
 */
public class WordNode {
   protected Word data;
   protected WordNode next;
   
   /**
   * Constructs a WordNode with the specified word data.
   *
   * @param word the word to be stored in this node
   */
   public WordNode(Word w)
   {
      data = w;
      next = null;
   }  // constructor
}  // class WordNode


