/**
 * Represents an unsorted list of words.
 * Extends the WordList class.
 */
public class UnsortedWordList extends WordList {
    /**
     * Constructs an empty UnsortedWordList.
     */
    public UnsortedWordList() {
        super(); // Call the constructor of the superclass WordList
    }
    /**
     * Adds a word to the unsorted list.
     * Simply appends the word to the end of the list.
     *
     * @param w The word to add
     */

    public void add(Word w) {
        append(w); 
    }
    /**
     * Checks if the word list contains a given word.
    * 
    * @param word The word to check for
     * @return true if the word is found in the list, false otherwise
     */
}
