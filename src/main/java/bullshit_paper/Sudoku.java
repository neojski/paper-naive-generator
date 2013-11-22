package bullshit_paper;

public class Sudoku {
    private final int[][] board;
    private final int n = 9;

    public Sudoku() {
        board = new int[n][n];
    }
    public void set(int i, int j, int value) {
        board[i][j] = value;
    }
    // this method returns 0 if field is empty
    public Integer get(int i, int j){
        return board[i][j];
    }
}
