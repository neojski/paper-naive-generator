package bullshit_paper;

import bullshit_paper_pdf.PDFRenderer;
import com.itextpdf.text.DocumentException;
import static java.lang.Math.min;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class OnetPlayground
{
    @Test
    public void test() throws Exception {

	List<SectionInfo> sectionInfos = Arrays.asList(new SectionInfo[] {
	    new SectionInfo(Arrays.asList("doda"), "Doda", java.awt.Color.PINK, true),
	    new SectionInfo(Arrays.asList("Smoleńsk"), "Smoleńsk", java.awt.Color.BLACK, false),
	    new SectionInfo(Arrays.asList("śnieg"), "Śnieg", java.awt.Color.BLUE, true) });
	new PaperGenerator().generate("Onet BP", sectionInfos, new FileOutputStream("onettest.pdf"));
    }
}