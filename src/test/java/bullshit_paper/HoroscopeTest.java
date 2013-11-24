package bullshit_paper;

import bullshit_paper_pdf.*;
import com.itextpdf.text.DocumentException;
import java.io.*;
import java.util.*;
import org.junit.*;

public class HoroscopeTest
{
    @Test
    public void test() throws DocumentException, IOException, RenderingException
    {
	String[] signs = new String[] { "baran", "byk", "bliznieta", "rak", "lew", "panna", "waga", "skorpion",
	    "strzelec", "koziorozec", "wodnik", "ryby" };
	List<PaperElement> horoscopeElements = new ArrayList<>();
	for (String sign : signs) horoscopeElements.add(new HoroscopeBuilder().buildEntry(sign, 20));
	List<PaperSection> sections = new ArrayList<>();
	sections.add(new PaperSection("Horoskop", horoscopeElements, java.awt.Color.ORANGE));	
	PDFRenderer renderer = new PDFRenderer();
	renderer.render(new FileOutputStream("horoskop.pdf"), "Horoskop", sections);
    }
    
}