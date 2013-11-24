package bullshit_paper.module.horoscope;

import bullshit_paper.PDFPaperFragment;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import java.io.IOException;

public class HoroscopePDFPaperFragment implements PDFPaperFragment {
    @Override
    public void render(Document doc) {
        try {
            BaseFont baseFont = BaseFont.createFont("font.ttf", BaseFont.IDENTITY_H, true);
            Font font = new Font(baseFont, 20.0f);
            Chapter chapter = new Chapter(new Paragraph("To będzie horoskop", font), 0);
            Section section = chapter.addSection(new Paragraph("Będzie Ci dobrze w życiu", font));
            
            doc.add(chapter);
        } catch (DocumentException | IOException ex) {
        }
    }
}
