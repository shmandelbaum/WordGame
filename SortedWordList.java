/**
 * Represents a sorted list of words.
 * Extends the WordList class.
 */

public class SortedWordList extends WordList {
    /**
     * Constructs an empty SortedWordList.
     */
    public SortedWordList() {
        super(); // Call the constructor of the superclass WordList
    }
    /**
     * Adds a word to the sorted list.
     * If the list is empty, the word is appended.
     * Otherwise, it inserts the word in sorted order.
     *
     * @param w The word to add
     */
    public void add (Word w){
        if (length == 0){ // If the list is empty, simply append the word
            append(w);
        } else {
            WordNode current = first.next;
            WordNode previous = first;
            // Traverse the list until the end or until finding the correct position
            while(current != null && current.data.compareTo(w) < 0) {
                previous = current;
                current = current.next;
            }
            WordNode n = new WordNode(w);
            n.next = current; // Insert the new node at the correct position
            previous.next = n;
            if(current == null) {
                last = n;
            }
            length++;
        }
    }
}
