package bullshit_paper_pdf;

import bullshit_paper.*;
import com.itextpdf.text.*;
import java.io.*;

class ElementsRenderer
{   
    public static PDFElement render(PaperElement elm, PDFStyle style) throws DocumentException, IOException
    {
	if (elm instanceof Article) return new ArticleRenderer().render((Article)elm, style);
	if (elm instanceof Sudoku) return new SudokuRenderer().render((Sudoku)elm, style);
	if (elm instanceof HoroscopeEntry) return new HoroscopeEntryRenderer().render((HoroscopeEntry)elm, style);
	return new PDFElement(null, false);
    }
}
