package com.example.sudoku;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class GridView {
    static final int GRID_SIZE = 9;
    private static final int SECTIONS_PER_ROW = 3;
    private static final int SECTION_SIZE = GRID_SIZE / SECTIONS_PER_ROW;
    private SudokuController controller;

    private Label[][] numberTiles; // the tiles/squares to show in the ui grid
    private TilePane numberPane;
    private EventHandler<MouseEvent> tileClickHandler;

    public GridView(SudokuController controller) {
        this.controller = controller;
        numberTiles = new Label[GRID_SIZE][GRID_SIZE];
        initNumberTiles();
        numberPane = makeNumberPane();
        tileClickHandler = createTileClickHandler();
        setTileClickHandlers();
    }

    // use this method to get a reference to the number (called by some other class)
    public TilePane getNumberPane() {
        return numberPane;
    }

    // called by constructor (only)
    private final void initNumberTiles() {
        Font font = Font.font("Monospaced", FontWeight.NORMAL, 20);

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Label tile = new Label(""); // Default to an empty string
                tile.setPrefWidth(32);
                tile.setPrefHeight(32);
                tile.setFont(font);
                tile.setAlignment(Pos.CENTER);
                tile.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;");
                // add your custom event handler
                tile.setOnMouseClicked(tileClickHandler);
                // add new tile to grid
                numberTiles[row][col] = tile;
            }
        }
    }

    private final TilePane makeNumberPane() {
        TilePane root = new TilePane();
        root.setPrefColumns(GRID_SIZE);
        root.setPrefRows(GRID_SIZE);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-border-color: black; -fx-border-width: 2px;");

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                root.getChildren().add(numberTiles[row][col]);
            }
        }

        return root;
    }

  

// ...

    private EventHandler<MouseEvent> createTileClickHandler() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for (int row = 0; row < GRID_SIZE; row++) {
                    for (int col = 0; col < GRID_SIZE; col++) {
                        if (event.getSource() == numberTiles[row][col]) {
                            // Prompt the user for input
                            TextInputDialog dialog = new TextInputDialog();
                            dialog.setTitle("Enter Value");
                            dialog.setHeaderText(null);
                            dialog.setContentText("Enter a value (1-9):");

                            // Show dialog and wait for user input
                            Optional<String> result = dialog.showAndWait();

                            // Check if the user entered a value
                            if (result.isPresent()) {
                                try {
                                    // Parse the user input as an integer
                                    int value = Integer.parseInt(result.get());

                                    // Call the appropriate controller method with the obtained value
                                    SudokuController.onFillInCell(row, col, value);
                                } catch (NumberFormatException e) {
                                    // Handle the case where the input is not a valid integer
                                    SudokuController.showInfoAlert("Invalid Input", "Please enter a valid number (1-9).");
                                }
                            }

                            // Exit the method after handling the click
                            return;
                        }
                    }
                }
            }
        };
    }

    public void setCellText(int row, int col, String text) {
        // Implement the logic to set the text of a cell in the GridView
        // ...
    }

    private void setTileClickHandlers() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                numberTiles[row][col].setOnMouseClicked(tileClickHandler);
            }
        }
    }
}
