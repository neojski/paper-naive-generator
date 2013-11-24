package bullshit_paper;

import bullshit_paper.module.article.AritclePDFPaperFragment;
import bullshit_paper.module.horoscope.HoroscopePDFPaperFragment;
import bullshit_paper.module.sudoku.SudokuPDFPaperFragment;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Generator {

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        File file = new File("test.pdf");
        Document doc = new Document();
        
        FileOutputStream stream = new FileOutputStream(file);
        PdfWriter.getInstance(doc, stream);
        doc.addTitle("some title");
        doc.open();

        new SudokuPDFPaperFragment().render(doc);
        new HoroscopePDFPaperFragment().render(doc);
        new AritclePDFPaperFragment().render(doc);
        
        doc.close();
    }
}
