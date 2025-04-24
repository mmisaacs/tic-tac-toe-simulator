package org.example;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    public boolean xTurn = true;
    private Button[][] board = new Button[3][3];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);

        grid.setStyle("-fx-padding: 20");
    }
}