import java.util.Scanner;

public class TicTacToe
{

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];
    private static String currentPlayer = "X";

    public static void main(String[] args)
    {
        playGame();
    }

    private static void playGame()
    {
        boolean gameOver = false;

        while (!gameOver) {
            clearBoard();

            while (!gameOver) {
                displayBoard();
                int rowMove = SafeInput.getRangedInt(new Scanner(System.in), "Enter row (1-3)", 1, 3) - 1;
                int colMove = SafeInput.getRangedInt(new Scanner(System.in), "Enter column (1-3)", 1, 3) - 1;

                while (!isValidMove(rowMove, colMove)) {
                    System.out.println("That space is occupied! you might want to try again.");
                    rowMove = SafeInput.getRangedInt(new Scanner(System.in), "Enter row (1-3)", 1, 3) - 1;
                    colMove = SafeInput.getRangedInt(new Scanner(System.in), "Enter column (1-3)", 1, 3) - 1;
                }

                board[rowMove][colMove] = currentPlayer;

                if (isWin(currentPlayer))
                {
                    displayBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameOver = true;
                }
                else if (isTie())
                {
                    displayBoard();
                    System.out.println("It's a tie!");
                    gameOver = true;
                }

                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            }

            if (SafeInput.getYNConfirm(new Scanner(System.in), "Do you want to play again?"))
            {
                gameOver = false;
            }
            else
            {
                System.out.println("Thanks for playing!");
                break;
            }
        }
    }

    private static void clearBoard()
    {
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                board[row][col] = " ";
            }
        }
    }

    private static void displayBoard()
    {
        for (int row = 0; row < ROWS; row++)
        {
            System.out.print("| ");
            for (int col = 0; col < COLS; col++)
            {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col)
    {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player)
    {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player)
    {
        for (int row = 0; row < ROWS; row++)
        {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++)
        {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        return false;
    }

    private static boolean isTie() {
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                if (board[row][col].equals(" "))
                {
                    return false;
                }
            }
        }
        return true;
    }
}

