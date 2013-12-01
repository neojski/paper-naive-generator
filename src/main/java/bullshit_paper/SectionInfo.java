package bullshit_paper;

import java.util.*;

public class SectionInfo
{
    private final List<String> _tags;
    private final String _name;
    private final java.awt.Color _headerColor;
    private final boolean _sudoku;

    public SectionInfo(List<String> tags, String name, java.awt.Color headerColor, boolean sudoku)
    {
	_tags = tags;
	_name = name;
	_headerColor = headerColor;
	_sudoku = sudoku;
    }
    
    public List<String> getTags() { return  _tags; }
    public String getName() { return _name; }
    public java.awt.Color getHeaderColor() { return  _headerColor; }
    public boolean  getSudoku() { return _sudoku; }
}