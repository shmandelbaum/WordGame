import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * FileMenuHandler class handles events from the FileMenu in the PuzzleGUI
 */

public class FileMenuHandler implements ActionListener {
    private PuzzleGUI puzzleGUI;
    /**
     * Constructs a new FileMenuHandler with a reference to the PuzzleGUI.
     *
     * @param puzzleGUI the PuzzleGUI instance to handle events for
     */
    public FileMenuHandler(PuzzleGUI puzzleGUI){
        this.puzzleGUI = puzzleGUI;
    }
    /**
     * Invoked when an action occurs in the FileMenu.
     * Handles events for "Open" and "Quit" menu items.
     *
     * @param event the ActionEvent representing the user's action
     */
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("Open")){ // Open file dialog to choose a file
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) { // Get the selected file and its path
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath(); // Read input file and set GUI with puzzle data
                PuzzleGUI.readInputFile(filePath);
                PuzzleGUI.setGui(PuzzleGUI.puzzleLetters, PuzzleGUI.solutions);
            }
        } else if (event.getActionCommand().equals("Quit")) { // Exit the program when "Quit" menu item is selected
            System.exit(0);
        }
    }
}

