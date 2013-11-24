package bullshit_paper_pdf;

import java.text.*;
import com.itextpdf.text.*;

class PDFStyle
{
    private final int _columnCount;
    private final float _columnMargin, _paragraphSpacing, _imageSpacing;
    private final DateFormat _dateFormat;
    private final Font _titleFont, _dateFont, _contentFont, _sectionTitleFont;
    private final BaseColor _sectionTitleColor;
    
    public PDFStyle(int columnCount, float columnMargin, float paragraphSpacing, float imageSpacing, DateFormat dateFormat, 
	    Font titleFont, Font dateFont, Font contentFont, Font sectionTitleFont, BaseColor sectionTitleColor)
    {
	_columnCount = columnCount;
	_columnMargin = columnMargin;
	_paragraphSpacing = paragraphSpacing;
	_imageSpacing = imageSpacing;
	_dateFormat = dateFormat;
	_titleFont = titleFont;
	_dateFont = dateFont;
	_contentFont = contentFont;
	_sectionTitleFont = sectionTitleFont;
	_sectionTitleColor = sectionTitleColor;
    }
    
    public int getColumntCount() { return _columnCount; }
    public float getColumnMargin() { return _columnMargin; }
    public float getParagraphSpacing() { return _paragraphSpacing; }
    public float getImageSpacing() { return _imageSpacing; }
    public DateFormat getDateFormat() { return _dateFormat; }
    public Font getTitleFont() { return _titleFont; }
    public Font getDateFont() { return _dateFont; }
    public Font getContentFont() { return _contentFont; }
    public Font getSectionTitleFont() { return _sectionTitleFont; }
    public BaseColor getSectionTitleColor() { return _sectionTitleColor; }
}
