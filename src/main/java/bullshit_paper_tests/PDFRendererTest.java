package bullshit_paper_tests;

import static org.junit.Assert.*;
import org.junit.Test;
import bullshit_paper.Article;
import bullshit_paper.IArticle;
import bullshit_paper.PDFRenderer;
import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.*;

public class PDFRendererTest
{

	@Test
	public void testRender() throws IOException, ParseException
	{
		FileOutputStream out = new FileOutputStream("tst.pdf");
		PDFRenderer renderer = new PDFRenderer();
		List<IArticle> arts = new ArrayList<>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (int i=1; i<=3; ++i) {
			List<String> input = Files.readAllLines(Paths.get("test_articles\\art" + i + ".txt"), StandardCharsets.UTF_8);
			StringBuilder contentBuilder = new StringBuilder();
			for (int j=2; j<input.size(); ++j) contentBuilder.append(input.get(j));
			arts.add(new Article(input.get(0), contentBuilder.toString(), dateFormat.parse(input.get(1)), null));
		}
		renderer.Render(out, "bullshitpaper01", arts);
	}

}
