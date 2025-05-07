# Tic-Tac-Toe Simulator
Simple GUI program that allows two players to play Tic-Tac-Toe
## GUI: JavaFX
1. GridPane: Creates GUI that hosts games
2. Board: 3x3 board to hold the buttons
3. Buttons: when pressed, will change to current player's role and color 

## Logic
1. **handleMove()**: this method takes the row and column clicked as a parameter and changes the board to match the user's move
2. **isBoardFull()**: this method returns true if all sections of the board are filled, returns false if not
3. **resetBoard()**: this is a void method that clears the board and sets it back to its original format
4. **showAlert()**: this method makes a JavaFX Alert and shows a popup when someone wins/ties and the game ends
5. **checkLine()**: this method returns true if 3 spaces in a row are equal
6. **checkWinner()**: this method uses the logic of the tic-tac-toe board and checks for the winnning cases
7. **setWinningTiles()**: to show the winning path, this method alternates the text color and background color of the winning tiles
