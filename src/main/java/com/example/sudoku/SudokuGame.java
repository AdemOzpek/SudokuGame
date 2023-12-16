package com.example.sudoku;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SudokuGame extends Application {
    private SudokuModel sudokuModel;
    private GridView gridView;
    private SudokuController sudokuController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create instances of the model, view, and controller.
        sudokuModel = new SudokuModel();
        sudokuController = new SudokuController(sudokuModel,gridView);
        gridView = new GridView(sudokuController);
        
        

        // Set up the JavaFX scene
        BorderPane root = new BorderPane();
        root.setTop(createMenuBar());
        root.setCenter(gridView.getNumberPane());
        root.setLeft(createLeftPanel());
        root.setBottom(createStatusBar());

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Sudoku Game");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        // Game menu
        Menu gameMenu = new Menu("Game");
        MenuItem newGameItem = new MenuItem("New Game");
        MenuItem chooseLevelItem = new MenuItem("Choose Level");
        gameMenu.getItems().addAll(newGameItem, chooseLevelItem);

        // File menu
        Menu fileMenu = new Menu("File");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem openItem = new MenuItem("Open");
        fileMenu.getItems().addAll(saveItem, openItem);

        // Help menu
        Menu helpMenu = new Menu("Help");
        MenuItem rulesItem = new MenuItem("Rules");
        helpMenu.getItems().addAll(rulesItem);

        menuBar.getMenus().addAll(gameMenu, fileMenu, helpMenu);

        // Set up event handlers for menu items
        newGameItem.setOnAction(e -> sudokuController.onNewGameSelected());
        chooseLevelItem.setOnAction(e -> sudokuController.onChooseLevelSelected());
        saveItem.setOnAction(e -> sudokuController.onSaveSelected());
        openItem.setOnAction(e -> sudokuController.onOpenSelected());
        rulesItem.setOnAction(e -> sudokuController.onRulesSelected());

        return menuBar;
    }

    private VBox createLeftPanel() {
        VBox leftPanel = new VBox(10);
        leftPanel.setPadding(new Insets(10));

        Button checkButton = new Button("Check");
        Button hintButton = new Button("Hint");

        // Set up event handlers for buttons
        checkButton.setOnAction(e -> sudokuController.onCheckSelected());
        hintButton.setOnAction(e -> sudokuController.onHintSelected());

        leftPanel.getChildren().addAll(checkButton, hintButton);
        return leftPanel;
    }

    private HBox createStatusBar() {
        HBox statusBar = new HBox();
        statusBar.setPadding(new Insets(5));
        statusBar.getChildren().add(new Label("Status:"));

        // You can add more status indicators or information as needed

        return statusBar;
    }
}
