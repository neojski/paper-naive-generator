package bullshit_paper_pdf;

import java.util.*;
import java.io.*;
import java.text.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import bullshit_paper.*;

public class PDFRenderer implements IRenderer
{   
    private final Document _doc;
    private final PDFStyle _style;
    private final Rectangle[] _columns;
    private final float _minElementHeight = 150f;
    
    public PDFRenderer() throws DocumentException, IOException
    {
	BaseFont titleBF = BaseFont.createFont("timesbd.ttf", BaseFont.IDENTITY_H, true);			
	Font titleFont = new Font(titleBF, 12.0f);
	BaseFont dateBF = BaseFont.createFont("timesi.ttf", BaseFont.IDENTITY_H, true);
	Font dateFont = new Font(dateBF, 8.0f);
	BaseFont contentBF = BaseFont.createFont("times.ttf", BaseFont.IDENTITY_H, true);
	Font contentFont = new Font(contentBF, 8.0f);	
	Font sectionTitleFont = titleFont;
	_style = new PDFStyle(3, 10f, 20f, 3f, new SimpleDateFormat("yyyy-MM-dd HH:mm"), titleFont, dateFont, contentFont, sectionTitleFont, BaseColor.WHITE);
	_doc = new Document();
	_columns = new Rectangle[_style.getColumntCount()];
	float width = (_doc.right()-_doc.left())/_style.getColumntCount();
	for (int i=0; i<_style.getColumntCount(); ++i) {
	    _columns[i] = new Rectangle(_doc.left() + i*width, _doc.bottom(), _doc.left() + (i+1)*width - _style.getColumnMargin(), _doc.top());
	}
    }

    @Override
    public void render(OutputStream stream, String title, java.util.List<PaperSection> sections) throws RenderingException
    {
	try {
	    PdfWriter writer = PdfWriter.getInstance(_doc, stream);
	    HeaderGenerator headerGen = new HeaderGenerator(_style.getSectionTitleFont(), _style.getSectionTitleColor());
	    writer.setPageEvent(headerGen);
	    _doc.addTitle(title);
	    _doc.open();
	    for (PaperSection section : sections) {
		_doc.newPage();
		headerGen.setSection(section.getTitle(), section.getHeaderColor());	
		renderSection(section, writer.getDirectContent());
	    }
	    _doc.close();
	}
	catch (DocumentException | IOException ex) {
	    throw new RenderingException(ex);
	}
    }
    
    private void renderSection(PaperSection section, PdfContentByte content) throws DocumentException, IOException
    {
	ColumnText columnText = new ColumnText(content);
	columnText.setExtraParagraphSpace(_style.getParagraphSpacing());
	columnText.setSimpleColumn(_columns[0]);
	columnText.setYLine(_columns[0].getTop());	
	int c = 0;
	for (PaperElement elm : section.getElements()) {
	    PDFElement pdfElm = ElementsRenderer.render(elm, _style);
	    columnText.addElement(pdfElm.getElement());
	    int status = columnText.go();
	    while (ColumnText.hasMoreText(status)) {
		if (c+1 < _style.getColumntCount()) ++c;
		else {
		    _doc.newPage();
		    c = 0;
		}
		columnText.setSimpleColumn(_columns[c]);
		columnText.setYLine(_columns[c].getTop());
		status = columnText.go();
	    }
	    if (columnText.getYLine() - _columns[c].getBottom() < _minElementHeight) columnText.setYLine(_columns[c].getBottom());
	}
    }  
    
}
