package bullshit_paper_tests;

import static org.junit.Assert.*;
import java.util.*;
import java.io.*;
import org.junit.Test;
import bullshit_paper.*;

public class PudelekArticleSourceTest
{

	@Test
	public void test() throws IOException
	{
		IArticleSource src = new PudelekArticleSource();
		List<String> tags = new ArrayList<>();
		tags.add("donald");
		tags.add("tusk");
		List<IArticle> arts = src.getArticles(tags);
		try (FileOutputStream out = new FileOutputStream("donaldtusk.pdf")) {
			new PDFRenderer().Render(out, "donald tusk", arts);
		}
	}

}
