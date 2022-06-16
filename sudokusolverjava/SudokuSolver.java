package sudokusolverjava;

public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {

        int[][] board = {
                { 8, 0, 1, 3, 5, 6, 0, 0, 0 },
                { 5, 0, 6, 0, 0, 7, 8, 0, 3 },
                { 3, 0, 9, 8, 0, 0, 0, 0, 7 },
                { 9, 1, 4, 6, 8, 3, 0, 0, 0 },
                { 0, 8, 0, 5, 1, 0, 0, 0, 4 },
                { 0, 0, 3, 2, 0, 0, 1, 9, 8 },
                { 0, 9, 0, 1, 4, 2, 5, 0, 0 },
                { 0, 0, 0, 0, 6, 8, 4, 7, 2 },
                { 0, 6, 0, 7, 3, 0, 0, 0, 1 }
        };
        System.out.println("Sudoku To Solved...");
        printBoard(board);

        System.out.println();
        System.out.println("Solving......");

        if (solveBoard(board)) {
            System.out.println("Solved...");
            System.out.println();
        } else {
            System.out.println("Unsolvable!");
        }

        printBoard(board);

    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {

            if (row % 3 == 0 && row != 0) {
                System.out.println("------------");
            }

            for (int column = 0; column < GRID_SIZE; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column]);

            }
            System.out.println();
        }
    }

    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) && !isNumberInColumn(board, number, column)
                && !isNumberInBox(board, number, row, column);
    }

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;

                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

}