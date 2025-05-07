package org.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.control.Label;

public class Main extends Application {
    public boolean xTurn = true;
    private Button[][] board = new Button[3][3];

    public Label turn;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);

        grid.setStyle("-fx-padding: 20; -fx-background-color: #f4f4f9; -fx-border-color: #333;");
        for (int row = 0; row< 3; row ++){
            for (int col = 0; col<3; col++){
                Button cell = new Button();
                cell.setPrefSize(100,100);
                cell.setFont(new Font(24));

                cell.setStyle("-fx-background-color: #fff; -fx-border-color: #333; -fx-border-width: 2px; -fx-font-weight: bold; -fx-text-fill: #333;");
                final int r = row;
                final int c = col;

                cell.setOnAction(e->handleMove(r,c));

                board[row][col]= cell;
                grid.add(cell, col, row);
            }
        }

        turn = new Label("Player X's turn");
        turn.setStyle("-fx-font-weight: bold");
        turn.setMaxWidth(Double.MAX_VALUE);
        turn.setAlignment(Pos.CENTER);
        grid.add(turn, 1,4);

        stage.setTitle("Tic Tac Toe");
        stage.setScene(new javafx.scene.Scene(grid,340,360));
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
                if(xTurn){
                    turn.setText("Player O's Turn");
                }
                else{
                    turn.setText("Player X's Turn");
                }
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
            if (checkLine(board[i][0], board[i][1], board[i][2])){
                setWinningTiles(i,0,i,1,i,2);
                turn.setText("Game Over");
                return true;
            }
            else if(checkLine(board[0][i],board[1][i],board[2][i])){
                setWinningTiles(0,i,1,i,2,i);
                turn.setText("Game Over");
                return true;
            }
        }

        if(checkLine(board[0][0], board[1][1], board[2][2])){
            setWinningTiles(0,0,1,1,2,2);
            turn.setText("Game Over");
            return true;
        }
        else if(checkLine(board[0][2], board[1][1], board[2][0])){
            setWinningTiles(0,2,1,1,2,0);
            turn.setText("Game Over");
            return true;
        }
        return false;
    }

    private void setWinningTiles(int x1, int y1, int x2, int y2, int x3, int y3){
        if(!xTurn){
            board[x1][y1].setStyle("-fx-background-color: #721c24; -fx-border-color: #333; -fx-border-width: 2px; -fx-font-weight: bold; -fx-text-fill: #ffdce0;");
            board[x2][y2].setStyle("-fx-background-color: #721c24; -fx-border-color: #333; -fx-border-width: 2px; -fx-font-weight: bold; -fx-text-fill: #ffdce0;");
            board[x3][y3].setStyle("-fx-background-color: #721c24; -fx-border-color: #333; -fx-border-width: 2px; -fx-font-weight: bold; -fx-text-fill: #ffdce0;");
        }
        else{
            board[x1][y1].setStyle("-fx-background-color: #004085; -fx-border-color: #333; -fx-border-width: 2px; -fx-font-weight: bold; -fx-text-fill: #d1e7ff;");
            board[x2][y2].setStyle("-fx-background-color: #004085; -fx-border-color: #333; -fx-border-width: 2px; -fx-font-weight: bold; -fx-text-fill: #d1e7ff;");
            board[x3][y3].setStyle("-fx-background-color: #004085; -fx-border-color: #333; -fx-border-width: 2px; -fx-font-weight: bold; -fx-text-fill: #d1e7ff;");
        }
    }
}