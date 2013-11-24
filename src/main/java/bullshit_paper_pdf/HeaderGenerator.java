package bullshit_paper_pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

class HeaderGenerator extends PdfPageEventHelper
{
    private final Font _titleFont;
    private final BaseColor _titleColor;
    private String _sectionTitle = null;
    private java.awt.Color _headerColor = null;
    
    public HeaderGenerator(Font titleFont, BaseColor titleColor)
    {
	_titleFont = titleFont;
	_titleColor = titleColor;
    }
    
    public void setSection(String title, java.awt.Color headerColor)
    {
	_sectionTitle = title;
	_headerColor = headerColor;
    }
    
    @Override
    public void onEndPage(PdfWriter writer, Document doc)
    {
	if (_sectionTitle == null) return;
	PdfContentByte content = writer.getDirectContent();
	content.saveState();
	content.setRGBColorFill(_headerColor.getRed(), _headerColor.getGreen(), _headerColor.getBlue());
	content.rectangle(doc.left(), doc.top()+doc.topMargin()/4, doc.right()-doc.left(), doc.topMargin()/2);
	content.fill();
	content.restoreState();
	content.saveState();
	content.beginText();
	content.setColorFill(_titleColor);
	content.setFontAndSize(_titleFont.getBaseFont(), _titleFont.getSize());
	content.showTextAligned(Element.ALIGN_LEFT, _sectionTitle, doc.left(), doc.top()+doc.topMargin()/2-_titleFont.getSize()/2, 0f);
	content.endText();
	content.restoreState();
    }
    
}
