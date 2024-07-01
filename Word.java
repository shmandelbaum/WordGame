/**
 * The Word class represents a word in the spelling bee game.
 * It encapsulates the word itself and provides methods for validation.
 */
public class Word {
    
    private String word;

    /**
     * Constructs a new Word with the specified string.
     *
     * @param word the string representing the word
     */
    public Word(String word) {
        this.word = word;
    }
    /**
     * To get the word you are workinf with
     * @return the String word
     */
    public String getWord() {
        return word;
    }
    /**
     * to make word into a character array
     * @return array of characters
     */
    public char[] toCharArray() {
        return word.toCharArray();
    }
    /**
     * to get the length of the word
     * @return word length
     */
    public int length() {
        return word.length();
    }
    /**
     * to get the character at a given index
     * @param index of the word as an array
     * @return the character at that index
     */
    public char charAt(int index) {
        return word.charAt(index);
    }
    /**
     * to make word as a lowercase string
     * @return word in lowecase
     */
    public String toLowerCase() {
        return word.toLowerCase();
    }
    /**
     * to get the index of a certain character in a word
     * @param c character you are looking for
     * @return the index of that character
     */
    public int indexOf(char c) {
        return word.indexOf(c);
    } 
    /**
     * toString method for word
     */
    @Override
    public String toString() {
        return word;
    }
    /**
     * overrides equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Word otherWord = (Word) obj;
        return word.equals(otherWord.word);
    }

    /**
     * Checks if the word contains any lowercase letters.
     * Throws an IllegalWordException if any lowercase letters are found.
     *
     * @throws IllegalWordException if the word contains non-lowercase letters
     */
    public void validate() throws IllegalWordException {
        for (char c : word.toCharArray()) {
            if (!Character.isLowerCase(c)) {
                System.out.println(word);
                throw new IllegalWordException("The word contains non-lowercase letters.");
            }
        }
    }
    /**
     * Compares this word with the specified word for order.
     * Returns a negative integer, zero, or a positive integer as this word is less than, equal to, or greater than the specified word.
     * 
     * @param otherWord the word to be compared
     * @return a negative integer, zero, or a positive integer as this word is less than, equal to, or greater than the specified word
     */
    public int compareTo(Word otherWord) {
        return this.word.compareTo(otherWord.word);
    }
}