package bullshit_paper_tests;

import static org.junit.Assert.*;
import org.junit.Test;
import com.itextpdf.text.Chapter;
import bullshit_paper.IArticle;
import bullshit_paper.IArticleSource;
import bullshit_paper.OnetArticleSource;
import bullshit_paper.PDFRenderer;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class OnetArticleSourceTest
{

	@Test
	public void testGetArticles() throws IOException
	{
		IArticleSource src = new OnetArticleSource();
		List<String> tags = new ArrayList<>();
		tags.add("koniec");
		tags.add("swiata");
		List<IArticle> arts = src.getArticles(tags);
		try (FileOutputStream out = new FileOutputStream("koniecswiata.pdf")) {
			new PDFRenderer().Render(out, "koniec swiata", arts);
		}
	}

}
