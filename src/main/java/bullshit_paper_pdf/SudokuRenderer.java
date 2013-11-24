package bullshit_paper_pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import bullshit_paper.*;

class SudokuRenderer implements IElementRenderer<Sudoku>
{
    @Override
    public PDFElement render(Sudoku sudoku, PDFStyle style)
    {
	PdfDiv res = new PdfDiv();
	res.setSpacingAfter(style.getParagraphSpacing());
	Paragraph titleParagraph = new Paragraph("Sudoku", style.getTitleFont());
	titleParagraph.setAlignment(Element.ALIGN_CENTER);
	titleParagraph.setSpacingAfter(style.getImageSpacing());
	res.addElement(titleParagraph);
	PdfPTable table = new PdfPTable(9);
	for (int i=0; i<9; ++i) {
	    for (int j=0; j<9; ++j) {
		PdfPCell cell = new PdfPCell();
		if ((i+j)%2 == 0) cell.setBackgroundColor(BaseColor.YELLOW);
		else cell.setBackgroundColor(BaseColor.ORANGE);
		int x = sudoku.get(i, j);
		if (x == 0) cell.addElement(new Chunk(" ", style.getContentFont()));
		else cell.addElement(new Chunk(Integer.toString(x), style.getContentFont()));
		table.addCell(cell);
	    }
	}
	res.addElement(table);
	return new PDFElement(res, true);
    }
}
