package bullshit_paper;

import com.itextpdf.text.DocumentException;
import org.junit.Test;
import java.util.*;
import java.io.*;
import java.net.URL;
import java.text.*;
import bullshit_paper_pdf.*;

public class PDFRendererTest {
    private List<String> getResource(int i) throws IOException {
        String input = TestHelper.getResourceAsString(new File("test_articles", "art" + i + ".txt").getPath());
        return Arrays.asList(input.split("\n"));
    }

    @Test
    public void testRender() throws IOException, ParseException, DocumentException, RenderingException {

        PDFRenderer renderer = new PDFRenderer();
        List<IArticle> arts = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (int i = 1; i <= 5; ++i) {
            List<String> input = getResource(i);
            StringBuilder contentBuilder = new StringBuilder();
            int imgCount = Integer.parseInt(input.get(2));
            List<IImage> images = new ArrayList<>();
            for (int j=0; j<imgCount; ++j) images.add(new Image(new URL(input.get(3+j))));
            for (int j = 3+imgCount; j < input.size(); ++j) {
                contentBuilder.append(input.get(j));
            }
            arts.add(new Article(input.get(0), contentBuilder.toString(), dateFormat.parse(input.get(1)), null, images));
        }
	List<PaperElement> elements = new ArrayList<>();
	for (IArticle art : arts) elements.add((Article)art);
	List<PaperSection> sections = new ArrayList<>();
	sections.add(new PaperSection("bullshitpaper01", elements, java.awt.Color.BLUE));
        renderer.render(new FileOutputStream("tst.pdf"), "bullshitpaper01", sections);
    }


}