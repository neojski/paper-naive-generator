package bullshit_paper;

import org.junit.Test;
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
