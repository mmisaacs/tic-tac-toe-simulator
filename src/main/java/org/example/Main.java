package org.example;

import javafx.application.Application;
import javafx.scene.control.Alert;
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

        grid.setStyle("-fx-padding: 20; -fx-background-color: #f4f4f9; -fx-border-color: #333;");
        stage.setTitle("Tic Tac Toe");
        stage.setScene(new javafx.scene.Scene(grid));
        stage.show();
    }

    private void handleMove(int row, int col) {
        if (board[row][col].getText().isEmpty()) {
            board[row][col].setText(xTurn ? "X" : "O");

            if(xTurn) {
                board[row][col].setStyle("-fx-background-color: #d1e7ff; -fx-border-color: #333; -fx-border-width: 2px; -fx-font-weight: bold; -fx-text-fill: #004085;");
            } else {
                board[row][col].setStyle("-fx-background-color: #ffdce0; -fx-border-color: #333; -fx-border-width: 2px; -fx-font-weight: bold; -fx-text-fill: #721c24;");
            }

            if (checkWinner()) {
                showAlert((xTurn ? "X" : "O") + "wins!");
            } else if (isBoardFull()) {
                showAlert("It's a draw!");
            } else {
                xTurn = !xTurn;
            }
        }
    }

    private boolean isBoardFull() {
        for (int row = 0; row<3; row++) {
            for (int col = 0; col<3; col++) {
                if (board[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard(){
        for (int row = 0; row<3; row++) {
            for (int col = 0; col<3; col++) {
                board[row][col].setText("");
                board[row][col].setStyle("-fx-background-color: #fff; -fx-border-color: #333; -fx-border-width: 2px; -fx-font-weight: bold; -fx-text-fill: #333;");
            }
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        resetBoard();
    }

    private boolean checkLine(Button b1, Button b2, Button b3) {
        return !b1.getText().isEmpty() && b1.getText().equals(b2.getText()) &&b2.getText().equals(b3.getText());
    }

    private boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (checkLine(board[i][0], board[i][1], board[i][2]) ||
                    checkLine(board[0][i],board[1][i],board[2][i])){
                return true;
            }
        }
        return  checkLine(board[0][0], board[1][1], board[2][2]) ||
                checkLine(board[0][2], board[1][1], board[2][0]);
    }
}