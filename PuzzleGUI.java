import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.awt.event.*;
import java.io.File;
/**
 * PuzzleGUI class represents a GUI for a spelling bee game
 * It allows users to enter input words and validates them
 * against a set of given letters and solutions
 */
public class PuzzleGUI { 
    static JFrame myFrame;
    static Container cPane;
    static TextArea wordsFound, letters;
    static String puzzleLetters;
    static UnsortedWordList solutions = new UnsortedWordList();
    static int score = 0;
    static SortedWordList guessedWords = new SortedWordList();
    static JMenu fileMenu;

    
    /**
     * Initializes the GUI components
     */
    public void initializeGUI(){
        wordsFound = new TextArea();
        letters = new TextArea();
        myFrame = new JFrame();
        myFrame.setSize(400, 600);
        myFrame.setLocation(200,200);
        myFrame.setTitle("Spelling Bee");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createFileMenu();
        myFrame.setVisible(true);
    }
    /**
     * Creates the file menu for the GUI
     */
    private void createFileMenu() {
        JMenuItem item;
        JMenuBar menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        FileMenuHandler fmh = new FileMenuHandler(this);

        item = new JMenuItem("Open");    //Open...
        item.addActionListener(fmh);
        fileMenu.add(item);

        fileMenu.addSeparator();           //add a horizontal separator line
    
        item = new JMenuItem("Quit");       //Quit
        item.addActionListener(fmh);
        fileMenu.add(item);
        
        myFrame.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
    }
    /**
     * Reads the input file containing the puzzle data
     * 
     * @param filePath The path to the input file
     */
    public static void readInputFile(String filePath) {
        TextFileInput inFile = new TextFileInput(filePath);
        puzzleLetters = inFile.readLine();
        String line;
        solutions = new UnsortedWordList();
        while((line = inFile.readLine()) != null){
            Word word = new Word(line);
            solutions.add(word);
        } 
        inFile.close();
    }
    /**
     * Set up the GUI layout and components
     * 
     * @param puzzleLetters The letters for this puzzle
     * @param solutions The array of solutiong for this puzzle
     */
    public static void setGui(String puzzleLetters, UnsortedWordList solutions){
        myFrame.setLayout(new GridLayout(1,2));
        cPane = myFrame.getContentPane();
        cPane.add(letters);
        cPane.add(wordsFound);
        letters.append("Letters: \n" + puzzleLetters + " \n");
        wordsFound.setText("Score: " + score + "\nWords Found: \n");
        myFrame.setVisible(true);
        while(true) {
        String input = JOptionPane.showInputDialog("Please enter a word that is at least five letters long: ");
        if (input == null) { // If user cancels input dialog
            // Enable the quit menu item
            fileMenu.getItem(1);
            break; // Exit the loop
        }
        if (input != null){ // if user enters a word it needs to be validated
            Word word = new Word(input);
            try {
                word.validate();
                boolean validGuess = validateGuess(word);
                if (validGuess) { // if users word is valid need to display it
                    score += calculateScore(word);
                    wordsFound.setText(getGuessedWordsString());
                    updateScoreDisplay();
                    if(guessedAllWords()) {
                    int playAgain = JOptionPane.showConfirmDialog(myFrame, "Congratulations! You found all the words!\nWould you like to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
                    if(playAgain == JOptionPane.YES_OPTION){
                        resetGame();
                    } else {
                        System.exit(0);
                        }
                    }
                }
            } catch (IllegalWordException e) {
                JOptionPane.showMessageDialog(myFrame, e.getMessage());
            }
        } 
    }   
}

    
    /**
     * Validates the user's input against the given letters and solutions
     * 
     * @param word The user's input word
     * @return true if the guess is valid, false otherwise
     */
    public static boolean validateGuess(Word word) {
        if(word.length() < 5) { // the word must be 5 letters
            JOptionPane.showMessageDialog(myFrame, "Your guess must be at least five letters long");
            return false;
        }
        for (int i = 0; i < word.length(); i++) { // to check that the letters guessed are in the word
            char c = word.charAt(i);
            if (puzzleLetters.indexOf(Character.toLowerCase(c)) == -1) {
                JOptionPane.showMessageDialog(myFrame, "You used a letter that is not one of the given letters");
                return false;
            }
        }
        char firstLetter = puzzleLetters.toLowerCase().charAt(0);
        boolean containsFirstLetter = word.toLowerCase().contains(Character.toString(firstLetter));
        if (!containsFirstLetter) {
            JOptionPane.showMessageDialog(myFrame, "Your guess must contain the first letter of the subject letters");
            return false;
        }
        WordNode current = guessedWords.first.next;
        while(current != null) {
            if(current.data.getWord().equals(word.getWord())) {
                return false;
            }
            current = current.next;
        }
        if (solutions.contains(word)) {
            guessedWords.add(word);
            return true;
        }
        JOptionPane.showMessageDialog(myFrame, "Your guess is not a valid solution.");
        return false;
    }
    /**
     * Updates the score display
     */
    private static void updateScoreDisplay() {
        wordsFound.setText("Score: " + score + "\nWords Found: \n" + guessedWords.toString() + "\n");
    }
    /**
     * Calculates the score for the given word based on the puzzle letters
     *
     * @param word The word for which the score is to be calculated
     * @return The score calculated for the word
     */
    private static int calculateScore(Word word) {
        char[] puzzleLettersArray = puzzleLetters.toLowerCase().toCharArray();
        for(char letter : puzzleLettersArray) { 
            if(word.getWord().indexOf(letter) == -1) return 1; // if the word does not contain all the puzzle letters, you get one point
        }
        return 3;
    }
     /**
     * Generates a string representation of the words found by the user
     *
     * @return A string containing the words found by the user
     */
    private static String getGuessedWordsString() {
        WordNode current = guessedWords.first.next; // go through the nodes in SortedWordList
        StringBuilder sb = new StringBuilder();
        sb.append("Words Found: \n");
        while (current != null) {
            sb.append(current.data).append("\n");
            current = current.next;
        }
        return sb.toString();
    }
     /**
     * Check if all the words in the solutions list have been guessed
     *
     * @return true if all words have been guessed, false otherwise
     */
    private static boolean guessedAllWords() {
        WordNode current = solutions.first.next;
        while(current != null) {
            if(!guessedWords.contains(current.data)) {
                return false;
            }
            current = current.next;
        }
        return true;
    }
    /**
     * Reset the game to play again
     */
    private static void resetGame() {
        score = 0;
        guessedWords = new SortedWordList();
        wordsFound.setText("Score: " + score + "\nWords Found: \n");
    }
}
