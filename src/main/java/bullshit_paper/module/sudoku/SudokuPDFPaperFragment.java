package bullshit_paper.module.sudoku;

import bullshit_paper.PDFPaperFragment;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import java.io.IOException;

public class SudokuPDFPaperFragment implements PDFPaperFragment {

    @Override
    public void render(Document doc) {
        try {
            BaseFont baseFont = BaseFont.createFont("font.ttf", BaseFont.IDENTITY_H, true);
            Font font = new Font(baseFont, 20.0f);
            Chapter chapter = new Chapter(new Paragraph("To będzie sudoku", font), 0);
            
            SudokuGenerator sudokuGenerator = new SudokuGenerator();
            Sudoku sudoku = sudokuGenerator.generate();
            
            Section section = chapter.addSection("Sudoku");
            for (int i = 0; i < 9; i++) {
                String row = "";
                for (int j = 0; j < 9; j++) {
                    row += sudoku.get(i, j) + " ";
                }
                section.add(new Chunk(row + "\n"));
            }

            doc.add(chapter);
        } catch (DocumentException | IOException ex) {
        }
    }
}
