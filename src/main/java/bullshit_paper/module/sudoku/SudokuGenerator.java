package bullshit_paper.module.sudoku;

import java.util.Random;

public class SudokuGenerator {
    private final Random r = new Random();
    public Sudoku generate() {
        int n = 9;
        Sudoku s = new Sudoku();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (r.nextInt(9) < 2) {
                    s.set(i, j, r.nextInt(10));
                }
            }
        }
        return s;
    }
    public static void main(String[] args) {
        Sudoku s = new SudokuGenerator().generate();
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(s.get(i,j) + " ");
            }
            System.out.println();
        }
    }
}
