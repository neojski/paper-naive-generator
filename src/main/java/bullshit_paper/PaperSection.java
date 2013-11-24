package bullshit_paper;

import java.util.*;

public class PaperSection
{
    private final String _title;
    private final List<PaperElement> _elements;
    private final java.awt.Color _headerColor;
    
    public PaperSection(String title, List<PaperElement> elements, java.awt.Color headerColor)
    {
	_title = title;
	_elements = elements;
	_headerColor = headerColor;
    }
	  
    public String getTitle() { return _title; }
    public List<PaperElement> getElements() { return _elements; }
    public java.awt.Color getHeaderColor() { return _headerColor; }
}
