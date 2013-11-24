package bullshit_paper_pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import bullshit_paper.*;
import java.io.*;

class ArticleRenderer implements IElementRenderer<IArticle>
{
    @Override
    public PDFElement render(IArticle art, PDFStyle style) throws DocumentException, IOException
    {
	PdfDiv res = new PdfDiv();
	res.setSpacingAfter(style.getParagraphSpacing());
	res.addElement(new Paragraph(art.getTitle(), style.getTitleFont()));
	res.addElement(new Paragraph(style.getDateFormat().format(art.getDate()), style.getDateFont()));
	if (art.getImages() != null) {
	    for (IImage img : art.getImages()) {
		Image imgElm = Image.getInstance(img.getImage(), null);
		imgElm.setSpacingBefore(style.getImageSpacing());
		imgElm.setSpacingAfter(style.getImageSpacing());	
		res.addElement(imgElm);
	    }
	}	
	Paragraph contentParagraph = new Paragraph(art.getContent(), style.getContentFont());
	contentParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
	res.addElement(contentParagraph);
	return new PDFElement(res, false);
    }
}
