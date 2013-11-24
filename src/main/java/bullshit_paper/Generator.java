package bullshit_paper;

import bullshit_paper.module.article.AritclePDFPaperFragment;
import bullshit_paper.module.horoscope.HoroscopePDFPaperFragment;
import bullshit_paper.module.sudoku.SudokuPDFPaperFragment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Generator {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("test.pdf");
        FileOutputStream stream = new FileOutputStream(file);
        
        new SudokuPDFPaperFragment().render(stream);
        new HoroscopePDFPaperFragment().render(stream);
        new AritclePDFPaperFragment().render(stream);
    }
}
