public class SudokuSolver {
    public static final int SIZE = 9; // 9x9 Sudoku grid

    public static void main(String[] args) {
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (solveSudoku(board)) {
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }

    // Backtracking function to solve Sudoku
    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) { // empty cell
                    for (int number = 1; number <= SIZE; number++) {
                        if (isValid(board, row, col, number)) {
                            board[row][col] = number;

                            if (solveSudoku(board)) {
                                return true;
                            } else {
                                board[row][col] = 0; // backtrack
                            }
                        }
                    }
                    return false; // no valid number found
                }
            }
        }
        return true; // solved
    }

    // Check if placing number is valid
    public static boolean isValid(int[][] board, int row, int col, int number) {
        // Check row
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == number) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == number) {
                return false;
            }
        }

        // Check 3x3 sub-grid
        int subRow = (row / 3) * 3;
        int subCol = (col / 3) * 3;
        for (int i = subRow; i < subRow + 3; i++) {
            for (int j = subCol; j < subCol + 3; j++) {
                if (board[i][j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    // Print the Sudoku board
    public static void printBoard(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
