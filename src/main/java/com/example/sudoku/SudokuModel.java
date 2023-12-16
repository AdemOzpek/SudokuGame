
package com.example.sudoku;
import java.io.Serializable;

public class SudokuModel implements Serializable {
    private int[][][] sudokuMatrix;  // [row][col][0] = initial values, [row][col][1] = solution
    private boolean[][][] userChoices;  // [row][col][0] = is initial value, [row][col][1] = user choice

    public SudokuModel() {
        sudokuMatrix = new int[9][9][2];
        userChoices = new boolean[9][9][2];
        // Initialize the matrices (you might want to populate them based on user preferences or difficulty level)
    }

    public int getCellValue(int row, int col) {
        return sudokuMatrix[row][col][1];  // Return the solution value
    }

    public boolean isInitialValue(int row, int col) {
        return userChoices[row][col][0];
    }

    public boolean setUserChoice(int row, int col, int value) {
        // Check if the cell is modifiable
        if (!userChoices[row][col][0]) {
            // The cell is not modifiable (initial value), return false
            return false;
        }

        // Check if the value is within the valid range (1-9)
        if (value < 1 || value > 9) {
            // Invalid value, return false
            return false;
        }

        // Set the user choice and return true
        userChoices[row][col][1] = true;  // Indicates user choice
        sudokuMatrix[row][col][1] = value;  // Update the solution value
        return true;
    }

    public void clearUserChoice(int row, int col) {
        // Check if the cell is modifiable
        if (!userChoices[row][col][0]) {
            // The cell is not modifiable (initial value), do nothing
            return;
        }

        // Clear the user choice
        userChoices[row][col][1] = false;
        sudokuMatrix[row][col][1] = 0;  // Reset the solution value
    }

    // Add more methods as needed for your Sudoku game logic

    // For example, you might want to add methods to check if the puzzle is solved,
    // generate a new puzzle, or load a puzzle from a file.
    public boolean checkIfSolved() {
        // Implement the logic to check if the Sudoku puzzle is solved
        // ...
        return false; // Replace with the actual logic
    }

    public void setSudokuMatrix(int[][][] newMatrix) {
        // Implement the logic to set the Sudoku matrix in the model
        // ...
    }
    

}
