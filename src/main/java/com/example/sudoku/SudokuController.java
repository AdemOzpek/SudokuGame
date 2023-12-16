package com.example.sudoku;



import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class SudokuController {
    private static SudokuModel sudokuModel;
    private static GridView gridView;

    public SudokuController(SudokuModel sudokuModel, GridView gridView) {
        this.sudokuModel = sudokuModel;
        this.gridView = gridView;
        initialize();
    }

    private void initialize() {
        // Set up event handlers for user interactions.
        // Connect model updates to view updates.
    }

    public void onNewGameSelected() {
        SudokuUtilities.SudokuLevel selectedLevel = null;// Get the selected level from user input
        int[][][] newGameMatrix = SudokuUtilities.generateSudokuMatrix(selectedLevel);
        sudokuModel.setSudokuMatrix(newGameMatrix);
        updateView(); // Make sure this updates the view correctly
    }

    public void onChooseLevelSelected() {
        // Implement logic to let the user choose the difficulty level
        // You might use a dialog or another user interface component
    }

    public void onSaveSelected() {
        // Implement logic to save the game state to a file
        // For example, you can use JavaFX FileChooser
        // ...
    }

    public void onOpenSelected() {
        // Implement logic to load a game state from a file
        // For example, you can use JavaFX FileChooser
        // ...
        updateView();
    }

    public void onRulesSelected() {
        // Implement logic to show the rules to the user
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sudoku Rules");
        alert.setHeaderText("How to Play Sudoku");
        alert.setContentText("..."); // Add the rules content here
        alert.showAndWait();
    }

    public void onCheckSelected() {
        // Implement logic to check if the puzzle is solved
        boolean isSolved = sudokuModel.checkIfSolved();
        if (isSolved) {
            showInfoAlert("Congratulations!", "You solved the Sudoku puzzle!");
        } else {
            showInfoAlert("Not Yet Solved", "Keep trying!");
        }
    }

    public void onHintSelected() {
        // Implement logic to provide a hint to the user
        // You might reveal a correct value in a random empty cell
        // ...
        updateView();
    }

    // New method to handle user input when clicking on a cell
    public static void onFillInCell(int row, int col, int value) {
        boolean success = sudokuModel.setUserChoice(row, col, value);
        if (!success) {
            // Handle the case where the user input is not valid
            showInfoAlert("Invalid Input", "Choose a different value for this cell.");
        }
        updateView();
    }

    public static void updateView() {
        // Implement logic to update the GridView based on the model
        // For example, iterate through the cells and update the labels
        for (int row = 0; row < GridView.GRID_SIZE; row++) {
            for (int col = 0; col < GridView.GRID_SIZE; col++) {
                int value = sudokuModel.getCellValue(row, col);
                gridView.setCellText(row, col, Integer.toString(value));
                // You may also apply different styles for initial values, user choices, etc.
                // ...
            }
        }
    }

    public static void showInfoAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
