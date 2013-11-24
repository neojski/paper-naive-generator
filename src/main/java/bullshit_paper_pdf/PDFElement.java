package bullshit_paper_pdf;

import com.itextpdf.text.*;

class PDFElement
{
    private final Element _element;
    private final boolean _dontBreak;
    
    public PDFElement(Element element, boolean dontBreak)
    {
	_element = element;
	_dontBreak = dontBreak;
    }
    
    public Element getElement() { return _element; }
    public boolean dontBreak() { return _dontBreak; }
}
