/**
 * Abstract class representing a linked list of words.
 */
public abstract class WordList {

	/** First node in word list - dummy node */
	protected WordNode first;
	/** Last node in word list */
	protected WordNode last;
	/** Number of data items in the list. */
	protected int length;

	public WordList() {
		first = new WordNode(null);
		last = first;
		length = 0;
	}

	/**
	 * Gets the number of data values currently stored in this LinkedList.
	 * 
	 * @return the number of elements in the list.
	 */

	public int getLength() {
		return length;
	}

	/**
	 * Appends a String data element to this WordList.
	 * 
	 * @param w the data element to be appended.
	 *         
	 */
	public void append(Word w){
		WordNode n = new WordNode(w);
		last.next = n;
		last = last.next;
		last.next = null;
		length++;
	} // method append(String)


	/**
	 * @return String representation of elements in word list delimited by a
	 *         space character
	 */
	public String toString() {
		WordNode p = first.next;
		String returnString = "";
		while (p != null) {
			returnString += p.data + " \n";
			p = p.next;
		}
		return returnString;
	}
	public boolean contains(Word w) {
        WordNode current = first.next;
        while (current != null) {
            if (current.data.equals(w)) {
                return true;
            }
            current = current.next;
        }
    return false;
    }
} // class WordList
