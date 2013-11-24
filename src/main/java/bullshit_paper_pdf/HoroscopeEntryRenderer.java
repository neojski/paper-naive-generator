package bullshit_paper_pdf;

import bullshit_paper.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.*;

class HoroscopeEntryRenderer implements IElementRenderer<HoroscopeEntry>
{
    @Override
    public PDFElement render(HoroscopeEntry entry, PDFStyle style) throws DocumentException, IOException
    {
	PdfDiv res = new PdfDiv();
	res.setSpacingAfter(style.getParagraphSpacing());
	res.addElement(new Paragraph(entry.getZodiacSign(), style.getTitleFont()));	
	Paragraph contentParagraph = new Paragraph(entry.getContent(), style.getContentFont());
	contentParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
	res.addElement(contentParagraph);
	return new PDFElement(res, false);	
    }
}
